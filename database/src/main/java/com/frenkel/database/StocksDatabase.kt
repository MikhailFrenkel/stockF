package com.frenkel.database

import com.frenkel.database.models.NewsDbo
import com.frenkel.database.models.StockDbo
import kotlinx.coroutines.flow.Flow

interface StocksDatabase  {
    fun observeAll(): Flow<List<StockDbo>>
    fun observeTrending(): Flow<List<StockDbo>>
    fun observeFavoriteStocks(): Flow<List<StockDbo>>

    suspend fun upsert(stocks: List<StockDbo>)
    suspend fun insert(stock: StockDbo)
    suspend fun remove(stocks: List<StockDbo>)
    suspend fun clean()
    suspend fun get(symbol: String): StockDbo?

    suspend fun getMarketNews(): List<NewsDbo>
    suspend fun insert(news: List<NewsDbo>)
}