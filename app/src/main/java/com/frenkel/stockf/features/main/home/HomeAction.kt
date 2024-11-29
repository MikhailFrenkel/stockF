package com.frenkel.stockf.features.main.home

import com.frenkel.stockf.features.main.models.StockUI

sealed interface HomeAction {
    data class OnStockItemClick(val stock: StockUI): HomeAction
}