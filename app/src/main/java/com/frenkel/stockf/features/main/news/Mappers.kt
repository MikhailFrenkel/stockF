package com.frenkel.stockf.features.main.news

import com.frenkel.data.models.NewsDto
import com.frenkel.data.models.RequestResult
import com.frenkel.stockf.features.common.toUI
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

internal fun RequestResult<List<NewsDto>>.toState(): NewsState {
    return when (this) {
        is RequestResult.Error -> NewsState(
            isLoading = false,
            news = data?.map { it.toUI() }?.toImmutableList() ?: persistentListOf(),
            error = error?.message ?: "Sth went wrong!"
        )

        is RequestResult.InProgress -> NewsState(
            isLoading = true,
            news = data?.map { it.toUI() }?.toImmutableList() ?: persistentListOf(),
        )

        is RequestResult.Success -> NewsState(
            isLoading = false,
            news = data.map { it.toUI() }.toImmutableList()
        )
    }
}