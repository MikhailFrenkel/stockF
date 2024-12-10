package com.frenkel.stockf.features.main.markets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frenkel.data.StocksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

class MarketsViewModel(
    private val repository: StocksRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val state = _query
        .debounce(500.milliseconds)
        .flatMapConcat { onSearch(it) }
        .onEach { state -> println("MarketsState: $state") }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MarketsState()
        )

    fun onAction(action: MarketsAction) {
        when (action) {
            is MarketsAction.OnSearch -> _query.value = action.query
        }
    }

    private fun onSearch(query: String): Flow<MarketsState> {
        return flow {
            if (query.isBlank()) {
                emit(MarketsState())
            } else {
                emitAll(repository.searchSymbol(query).map { it.toState(query) })
            }
        }
    }
}