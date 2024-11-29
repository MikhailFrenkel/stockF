package com.frenkel.stockf.features.stock_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.data.FinnhubRepository
import com.frenkel.data.models.RequestResult
import com.frenkel.stockf.features.main.models.toDisplayableNumber
import com.frenkel.stockf.features.stock_details.models.StockInfoUI
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class StockDetailsViewModel(
    symbol: String,
    finnhubRepository: FinnhubRepository
) : ViewModel() {
    private val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private val _state = MutableStateFlow(StockDetailState())
    val state: StateFlow<StockDetailState> = _state

    init {
        viewModelScope.launch {
            val companyProfileDeferred = async {
                finnhubRepository.getCompanyProfile2(symbol)
            }

            val quoteDeferred = async {
                finnhubRepository.getQuote(symbol)
            }

            val companyProfileResponse = companyProfileDeferred.await()
            val quoteResponse = quoteDeferred.await()

            if (companyProfileResponse.data != null && quoteResponse.data != null) {
                _state.update { it.copy(
                    isLoading = false,
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
                        date = dateFormatter.format(quoteResponse.data!!.date),
                        country = companyProfileResponse.data!!.country,
                        exchange = companyProfileResponse.data!!.exchange,
                        industry = companyProfileResponse.data!!.finnhubIndustry,
                        ipoDate = companyProfileResponse.data!!.ipo,
                        marketCapitalization = companyProfileResponse.data!!.marketCapitalization.toDisplayableNumber(),
                        shareOutstanding = companyProfileResponse.data!!.shareOutstanding.toDisplayableNumber(),
                        webUrl = companyProfileResponse.data!!.webUrl
                    )
                ) }
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
}