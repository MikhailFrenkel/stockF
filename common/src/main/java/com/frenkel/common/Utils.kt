package com.frenkel.common

import java.util.Calendar
import java.util.Date

fun Date.addMonth(month: Int): Date = this.modifyDate {
    add(Calendar.MONTH, month)
}

fun Date.modifyDate(
    block: Calendar.() -> Unit
): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    block(calendar)
    return calendar.time
}