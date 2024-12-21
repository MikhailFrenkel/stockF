package com.frenkel.stockf

import com.frenkel.common.addMonth
import com.frenkel.stockf.utils.toFormattedDateString
import org.junit.Assert
import org.junit.Test
import java.util.Calendar

class UtilsTest {

    @Test
    fun toFormattedDateString_Test() {
        val expected = "2024-01-10"
        val calendar = Calendar.getInstance()
        calendar.set(2024, Calendar.JANUARY, 10)

        val actual = calendar.time.toFormattedDateString()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun modifyDate_Test() {
        val expected = Calendar.getInstance().apply {
            set(2022, Calendar.NOVEMBER, 21)
        }.time

        val calendar = Calendar.getInstance()
        calendar.set(2021, Calendar.NOVEMBER, 21)

        val actual = calendar.time.addMonth(12)

        Assert.assertEquals(expected, actual)
    }
}