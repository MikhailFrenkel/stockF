package com.frenkel.stockf.utils

import android.content.Context
import com.frenkel.data.models.Symbol
import com.frenkel.stockf.R
import com.frenkel.stockf.features.stock_details.models.ChartTimeRange
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedDateString(): String {
    val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return formatter.format(this)
}

fun Date.toRelativeDateString(context: Context): String {
    val currentDate = Date() // Today's date
    val diffInMillis = currentDate.time - this.time
    val daysDifference = (diffInMillis / (1000 * 60 * 60 * 24)).toInt() // Convert millis to days

    return when (daysDifference) {
        0 -> context.getString(R.string.today)
        1 -> context.getString(R.string.yesterday)
        2 -> context.getString(R.string.two_days_ago)
        3 -> context.getString(R.string.three_days_ago)
        in 4..6 -> context.getString(R.string.days_ago, daysDifference)
        7 -> context.getString(R.string.a_week_ago)
        in 8..13 -> context.getString(R.string.over_a_week_ago)
        in 14..20 -> context.getString(R.string.two_weeks_ago)
        in 21..27 -> context.getString(R.string.three_weeks_ago)
        in 28..34 -> context.getString(R.string.a_month_ago)
        else -> this.toFormattedDateString()
    }
}

internal fun String.toSymbolIcon(): Int = when (this.uppercase()) {
    Symbol.NVDA.name -> R.drawable.ic_nvda
    Symbol.AAPL.name -> R.drawable.ic_aapl
    Symbol.MSFT.name -> R.drawable.ic_msft
    Symbol.AMZN.name -> R.drawable.ic_amzn
    Symbol.AMD.name -> R.drawable.ic_amd
    Symbol.ABNB.name -> R.drawable.ic_abnb
    Symbol.META.name -> R.drawable.ic_meta
    Symbol.GOOGL.name -> R.drawable.ic_googl
    Symbol.TSLA.name -> R.drawable.ic_tsla
    Symbol.JPM.name -> R.drawable.ic_jpm
    Symbol.V.name -> R.drawable.ic_v
    Symbol.MA.name -> R.drawable.ic_ma
    Symbol.NFLX.name -> R.drawable.ic_nflx
    Symbol.KO.name -> R.drawable.ic_ko
    Symbol.PEP.name -> R.drawable.ic_pep
    Symbol.MCD.name -> R.drawable.ic_mcd
    Symbol.QCOM.name -> R.drawable.ic_qcom
    else -> R.drawable.ic_unknown_logo
}