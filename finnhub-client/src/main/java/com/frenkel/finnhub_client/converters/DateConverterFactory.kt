package com.frenkel.finnhub_client.converters

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal class DateConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        if (type == Date::class.java) {
            return Converter<Date, String> {
                val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                formatter.format(it)
            }
        }

        return super.stringConverter(type, annotations, retrofit)
    }
}