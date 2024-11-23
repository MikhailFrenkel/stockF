package com.frenkel.database_android.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.frenkel.database_android.models.StockRoomDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface StocksDao {

    @Query("SELECT * FROM stocks")
    fun observeAll(): Flow<List<StockRoomDbo>>

    @Insert
    suspend fun insert(stocks: List<StockRoomDbo>)

    @Delete
    suspend fun remove(stocks: List<StockRoomDbo>)

    @Query("DELETE FROM stocks")
    suspend fun clean()
}