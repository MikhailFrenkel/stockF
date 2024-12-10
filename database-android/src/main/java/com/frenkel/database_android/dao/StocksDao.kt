package com.frenkel.database_android.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frenkel.database_android.models.StockRoomDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {

    @Query("SELECT * FROM stocks")
    fun observeAll(): Flow<List<StockRoomDbo>>

    @Query("SELECT * FROM stocks WHERE favorite = 1")
    fun observeFavoriteStocks(): Flow<List<StockRoomDbo>>

    @Query("SELECT * FROM stocks WHERE trending = 1")
    fun observeTrendingStocks(): Flow<List<StockRoomDbo>>

    @Query("SELECT * FROM stocks WHERE symbol = :symbol LIMIT 1")
    suspend fun get(symbol: String): StockRoomDbo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stocks: List<StockRoomDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: StockRoomDbo)

    @Delete
    suspend fun remove(stocks: List<StockRoomDbo>)

    @Query("DELETE FROM stocks")
    suspend fun clean()
}