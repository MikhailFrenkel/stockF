package com.frenkel.database_android.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.frenkel.database.models.CurrencyDbo
import com.frenkel.database.models.StockDbo

@Entity(tableName = "stocks")
data class StockRoomDbo(
    @PrimaryKey @ColumnInfo("symbol") val symbol: String,
    @ColumnInfo("description") val description: String,
    @Embedded("currency") val currency: CurrencyRoomDbo,
    @ColumnInfo("price") val price: Double? = null,
    @ColumnInfo("percentChange") val percentChange: Double? = null,
    @ColumnInfo("favorite") val favorite: Boolean = false,
    @ColumnInfo("imageUrl") val imageUrl: String? = null
) {
    fun mergeWith(other: StockRoomDbo?): StockRoomDbo {
        if (other == null)
            return this

        return copy(
            favorite = other.favorite,
            imageUrl = other.imageUrl
        )
    }
}

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
    symbol = symbol,
    description = description,
    currency = currency.toRoomDbo(),
    price = price,
    percentChange = percentChange,
    favorite = favorite,
    imageUrl = imageUrl
)

internal fun StockRoomDbo.toDbo(): StockDbo = StockDbo(
    symbol = symbol,
    description = description,
    currency = currency.toDbo(),
    price = price,
    percentChange = percentChange,
    favorite = favorite,
    imageUrl = imageUrl
)