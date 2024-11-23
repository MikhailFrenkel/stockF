package com.frenkel.stockf.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.data.FinnhubRepository
import com.frenkel.stockf.features.main.toHomeState
import com.frenkel.stockf.predefinedStockSymbols
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val finnhubRepository: FinnhubRepository
) : ViewModel() {

    val state = finnhubRepository.getStocksInfo(predefinedStockSymbols, viewModelScope)
        .map { it.toHomeState() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            HomeState()
        )
}