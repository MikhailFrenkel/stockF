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

    override fun observeFavoriteStocks(): Flow<List<StockDbo>> {
        return db.getStocksDao().observeFavoriteStocks()
            .map { stockRoomDbos ->
                stockRoomDbos.map { it.toDbo() }
            }
    }

    override suspend fun upsert(stocks: List<StockDbo>) {
        val mergedStocks = stocks.map { stock ->
            val stockRoomDbo = db.getStocksDao().get(stock.symbol)
            stock.toRoomDbo().mergeWith(stockRoomDbo)
        }

        db.getStocksDao().insert(mergedStocks)
    }

    override suspend fun insert(stock: StockDbo) {
        db.getStocksDao().insert(stock.toRoomDbo())
    }

    override suspend fun remove(stocks: List<StockDbo>) {
        db.getStocksDao().remove(stocks.map { it.toRoomDbo() })
    }

    override suspend fun clean() {
        db.getStocksDao().clean()
    }

    override suspend fun get(symbol: String): StockDbo? {
        return db.getStocksDao().get(symbol)?.toDbo()
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