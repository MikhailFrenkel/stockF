package com.frenkel.stockf.features.main.markets

import com.frenkel.data.models.RequestResult
import com.frenkel.data.models.SymbolLookupDto
import com.frenkel.stockf.features.main.markets.models.SymbolLookupUI
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

internal fun SymbolLookupDto.toUI(): SymbolLookupUI = SymbolLookupUI(
    description = description,
    displaySymbol = displaySymbol,
    symbol = symbol,
    type = type
)

internal fun RequestResult<List<SymbolLookupDto>>.toState(query: String): MarketsState {
    return when (this) {
        is RequestResult.Error -> MarketsState(error = error?.message ?: "Sth went wrong!")
        is RequestResult.InProgress -> MarketsState(
            isLoading = true,
            query = query,
            results = data?.map { it.toUI() }?.toImmutableList() ?: persistentListOf()
        )
        is RequestResult.Success -> MarketsState(
            query = query,
            results = data.map { it.toUI() }.toImmutableList()
        )
    }
}