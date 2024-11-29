package com.frenkel.stockf.utils

import com.frenkel.data.models.Symbol
import com.frenkel.stockf.R

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