package com.frenkel.database_android

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frenkel.database.StocksDatabase
import com.frenkel.database.models.StockDbo
import com.frenkel.database_android.dao.StocksDao
import com.frenkel.database_android.models.StockRoomDbo
import com.frenkel.database_android.models.toDbo
import com.frenkel.database_android.models.toRoomDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private class StocksRoomDatabaseImpl(
    private val db: StocksRoomDatabase
) : StocksDatabase {
    override fun observeAll(): Flow<List<StockDbo>> {
        return db.getStocksDao().observeAll()
            .map { stocksRoomDbo ->
                stocksRoomDbo.map { it.toDbo() }
            }
    }

    override suspend fun insert(stocks: List<StockDbo>) {
        db.getStocksDao().insert(stocks.map { it.toRoomDbo() })
    }

    override suspend fun remove(stocks: List<StockDbo>) {
        db.getStocksDao().remove(stocks.map { it.toRoomDbo() })
    }

    override suspend fun clean() {
        db.getStocksDao().clean()
    }

    override suspend fun cleanAndInsert(stocks: List<StockDbo>) {
        db.getStocksDao().cleanAndInsert(stocks.map { it.toRoomDbo() })
    }
}

@Database(entities = [StockRoomDbo::class], version = 1)
private abstract class StocksRoomDatabase : RoomDatabase() {

    abstract fun getStocksDao(): StocksDao
}

fun StocksRoomDatabase(applicationContext: Context): StocksDatabase {
    val db = Room.databaseBuilder(
        applicationContext,
        StocksRoomDatabase::class.java,
        "stocks_db"
    ).build()

    return StocksRoomDatabaseImpl(db)
}