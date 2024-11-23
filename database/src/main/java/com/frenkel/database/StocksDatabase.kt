package com.frenkel.database

import com.frenkel.database.models.StockDbo
import kotlinx.coroutines.flow.Flow

interface StocksDatabase  {
    fun observeAll(): Flow<List<StockDbo>>
    suspend fun insert(stocks: List<StockDbo>)
    suspend fun remove(stocks: List<StockDbo>)
    suspend fun clean()
}