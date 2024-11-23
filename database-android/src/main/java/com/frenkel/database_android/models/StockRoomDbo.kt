package com.frenkel.database_android.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frenkel.database.models.CurrencyDbo
import com.frenkel.database.models.StockDbo

@Entity(tableName = "stocks")
data class StockRoomDbo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("symbol") val symbol: String,
    @ColumnInfo("description") val description: String,
    @Embedded("currency") val currency: CurrencyRoomDbo,
    @ColumnInfo("price") val price: Double? = null,
    @ColumnInfo("percentChange") val percentChange: Double? = null,
)

data class CurrencyRoomDbo(
    val code: String,
    val symbol: String? = null,
)

internal fun CurrencyDbo.toRoomDbo(): CurrencyRoomDbo = CurrencyRoomDbo(
    code = code,
    symbol = symbol
)

internal fun CurrencyRoomDbo.toDbo(): CurrencyDbo = CurrencyDbo(
    code = code,
    symbol = symbol
)

internal fun StockDbo.toRoomDbo(): StockRoomDbo = StockRoomDbo(
    id = id,
    symbol = symbol,
    description = description,
    currency = currency.toRoomDbo(),
    price = price,
    percentChange = percentChange
)

internal fun StockRoomDbo.toDbo(): StockDbo = StockDbo(
    id = id,
    symbol = symbol,
    description = description,
    currency = currency.toDbo(),
    price = price,
    percentChange = percentChange
)