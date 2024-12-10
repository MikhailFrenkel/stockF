package com.frenkel.stockf.features.stock_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.common.addMonth
import com.frenkel.common.modifyDate
import com.frenkel.data.StocksRepository
import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.StockSymbolDto
import com.frenkel.polygon_client.models.Timespan
import com.frenkel.stockf.features.main.models.toDisplayableNumber
import com.frenkel.stockf.features.stock_details.models.ChartTimeRange
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import com.frenkel.stockf.utils.toFormattedDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class StockDetailsViewModel(
    private val symbol: String,
    private val repository: StocksRepository
) : ViewModel() {
    private val _state = MutableStateFlow(StockDetailState())
    val state: StateFlow<StockDetailState> = _state

    init {
        viewModelScope.launch {
            val companyProfileDeferred = async {
                withContext(Dispatchers.IO) {
                    repository.getCompanyProfile2(symbol)
                }
            }

            val quoteDeferred = async {
                withContext(Dispatchers.IO) {
                    repository.getQuote(symbol)
                }
            }

            val companyProfileResponse = companyProfileDeferred.await()
            val quoteResponse = quoteDeferred.await()

            if (companyProfileResponse.data != null && quoteResponse.data != null) {
                val favorite = repository.getStock(symbol).data?.favorite ?: false

                _state.update { it.copy(
                    isLoading = false,
                    isFavorite = favorite,
                    stockInfo = StockInfoUI(
                        symbol = companyProfileResponse.data!!.ticker,
                        companyName = companyProfileResponse.data!!.name,
                        logo = companyProfileResponse.data!!.logo,
                        currency = companyProfileResponse.data!!.currency,
                        price = quoteResponse.data!!.currentPrice.toDisplayableNumber(),
                        percentChange = quoteResponse.data!!.percentChange.toDisplayableNumber(),
                        highPriceOfTheDay = quoteResponse.data!!.highPriceOfTheDay.toDisplayableNumber(),
                        lowPriceOfTheDay = quoteResponse.data!!.lowPriceOfTheDay.toDisplayableNumber(),
                        openPriceOfTheDay = quoteResponse.data!!.openPriceOfTheDay.toDisplayableNumber(),
                        previousClosePrice = quoteResponse.data!!.previousClosePrice.toDisplayableNumber(),
                        date = quoteResponse.data!!.date.toFormattedDateString(),
                        country = companyProfileResponse.data!!.country,
                        exchange = companyProfileResponse.data!!.exchange,
                        industry = companyProfileResponse.data!!.finnhubIndustry,
                        ipoDate = companyProfileResponse.data!!.ipo,
                        marketCapitalization = companyProfileResponse.data!!.marketCapitalization.toDisplayableNumber(),
                        shareOutstanding = companyProfileResponse.data!!.shareOutstanding.toDisplayableNumber(),
                        webUrl = companyProfileResponse.data!!.webUrl
                    )
                ) }

                launch { subscribeToRealTimeTrade(symbol) }
                launch { loadCompanyNews() }
            } else {
                val errorMessage = (companyProfileResponse as? RequestResult.Error)?.error?.message
                    ?: (quoteResponse as? RequestResult.Error)?.error?.message
                    ?: "Sth went wrong!"

                _state.update { it.copy(
                    isLoading = false,
                    error = errorMessage
                ) }
            }
        }
    }

    fun onAction(action: StockDetailAction) {
        when (action) {
            is StockDetailAction.OnChartTimeRangeChanged -> onChartTimeRangeChanged(action.timeRange)
            is StockDetailAction.OnFavoriteChanged -> onFavoriteChanged(action.isFavorite)
        }
    }

    private suspend fun subscribeToRealTimeTrade(symbol: String) {
        repository.observeRealTimeTrades(listOf(symbol))
            .onEach { trades ->
                val trade = trades.data.firstOrNull { it.symbol == symbol }
                if (trade != null) {
                    _state.update {
                        if (it.stockInfo != null) {
                            it.copy(
                                stockInfo = it.stockInfo.copy(
                                    price = trade.lastPrice.toDisplayableNumber()
                                )
                            )
                        } else {
                            it
                        }
                    }
                }
            }
            .catch { it.printStackTrace() }
            .collect()
    }

    private suspend fun loadCompanyNews() {
        val requestResult = repository.getCompanyNews(symbol)
        if (requestResult.data?.isNotEmpty() == true) {
            _state.update {
                it.copy(
                    companyNews = requestResult.data!!.map { dto -> dto.toUI() }
                )
            }
        }
    }

    private fun onChartTimeRangeChanged(timeRange: ChartTimeRange) {
        when (timeRange) {
            ChartTimeRange.SEVEN_DAYS -> {
                if (_state.value.sevenDaysChartData == null) {
                    viewModelScope.launch { loadSevenDaysChartData() }
                }
            }

            ChartTimeRange.ONE_MONTH -> {
                if (_state.value.oneMonthChartData == null) {
                    viewModelScope.launch { loadOneMonthChartData() }
                }
            }

            ChartTimeRange.ONE_YEAR -> {
                if (_state.value.oneYearChartData == null) {
                    viewModelScope.launch { loadOneYearChartData() }
                }
            }
        }
    }

    private suspend fun loadSevenDaysChartData() {
        loadChartData(
            timespan = Timespan.DAY,
            from = Date().modifyDate { add(Calendar.DAY_OF_MONTH, -10) }
        ) { data ->
            _state.update {
                it.copy(
                    sevenDaysChartData = data
                )
            }
        }
    }

    private suspend fun loadOneMonthChartData() {
        loadChartData(
            timespan = Timespan.DAY,
            from = Date().modifyDate { add(Calendar.MONTH, -1) }
        ) { data ->
            _state.update {
                it.copy(
                    oneMonthChartData = data
                )
            }
        }
    }

    private suspend fun loadOneYearChartData() {
        loadChartData(
            timespan = Timespan.MONTH,
            from = Date().modifyDate { add(Calendar.YEAR, -1) }
        ) { data ->
            _state.update {
                it.copy(
                    oneYearChartData = data
                )
            }
        }
    }

    private suspend fun loadChartData(
        timespan: Timespan,
        from: Date,
        block: (Map<Date, Double>) -> Unit
    ) {
        val result = repository.getSymbolAggregates(
            symbol = symbol,
            multiplier = 1,
            timespan = timespan,
            from = from,
            to = Date(),
        )

        result.data?.let { data ->
            val chartData = data.results.associate { tradeDto ->
                tradeDto.date to tradeDto.closePrice
            }

            block(chartData)
        }
    }

    private fun onFavoriteChanged(isFavorite: Boolean) {
        _state.value.stockInfo?.let { stockInfo ->
            viewModelScope.launch {
                repository.updateOrSave(StockSymbolDto(
                    symbol = symbol,
                    description = stockInfo.companyName,
                    currency = stockInfo.currency,
                    price = stockInfo.price.value,
                    percentChange = stockInfo.percentChange.value,
                    favorite = isFavorite,
                    imageUrl = stockInfo.logo
                ))

                _state.update { it.copy(isFavorite = isFavorite) }
            }
        }
    }
}