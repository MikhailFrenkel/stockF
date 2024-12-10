package com.frenkel.data

import com.frenkel.data.models.NewsDto
import com.frenkel.data.models.RequestResult
import com.frenkel.database.StocksDatabase
import com.frenkel.finnhub_client.FinnhubApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow

interface NewsRepository {
    fun getMarketNews(): Flow<RequestResult<List<NewsDto>>>
}

class NewsRepositoryImpl(
    private val finnhubApi: FinnhubApi,
    private val db: StocksDatabase
) : NewsRepository {

    override fun getMarketNews(): Flow<RequestResult<List<NewsDto>>> {
        return flow {
            emit(RequestResult.InProgress())

            val cachedResult = getMarketNewsFromCache()
            if (cachedResult !is RequestResult.Error)
                emit(cachedResult)

            val serverResult = getMarketNewsFromServer()
            emit(serverResult)

            serverResult.data?.let { dtos ->
                db.insert(dtos.map { it.toDbo() })
            }
        }
    }

    private suspend fun getMarketNewsFromCache(): RequestResult<List<NewsDto>> {
        try {
            val news = db.getMarketNews().map { it.toDto() }
            return RequestResult.InProgress(data = news.ifEmpty { null })
        } catch (e: Exception) {
            e.printStackTrace()
            return RequestResult.Error(error = e)
        }
    }

    private suspend fun getMarketNewsFromServer(): RequestResult<List<NewsDto>> {
        try {
            return finnhubApi.fetchMarketNews()
                .toRequestResult()
                .map { news ->
                    news.map { it.toDto() }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            return RequestResult.Error(error = e)
        }
    }
}