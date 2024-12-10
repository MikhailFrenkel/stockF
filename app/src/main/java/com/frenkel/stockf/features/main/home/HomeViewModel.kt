package com.frenkel.stockf.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.data.StocksRepository
import com.frenkel.stockf.predefinedStockSymbols
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(
    repository: StocksRepository
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        repository.getTrendingStocks(predefinedStockSymbols, viewModelScope)
            .map { it.toHomeState { stocks ->
                _state.value.copy(
                    isLoading = false,
                    stocks = stocks.map { it.toStockUI() }.toImmutableList()
                )
            } }
            .onEach { state -> _state.update { state } }
            .launchIn(viewModelScope)

        repository.observeFavoriteStocks()
            .map { it.toHomeState { stocks ->
                _state.value.copy(
                    favoriteStocks = stocks
                        .map { it.toStockUI() }
                        .toImmutableList()
                        .ifEmpty { null }
                )
            } }
            .onEach { state -> _state.update { state } }
            .launchIn(viewModelScope)
    }
}