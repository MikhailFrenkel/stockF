package com.frenkel.stockf.features.main.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.data.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun onAction(action: NewsAction) {
        when (action) {
            NewsAction.OnPullToRefresh -> loadData()
        }
    }

    private fun loadData() {
        newsRepository.getMarketNews()
            .map { it.toState() }
            .onEach { state -> _state.update { state } }
            .launchIn(viewModelScope)
    }
}