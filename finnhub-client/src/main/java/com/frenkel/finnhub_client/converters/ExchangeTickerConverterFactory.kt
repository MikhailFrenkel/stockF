package com.frenkel.finnhub_client.converters

import com.frenkel.finnhub_client.models.ExchangeTicker
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class ExchangeTickerConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        if (type == ExchangeTicker::class.java) {
            return Converter<ExchangeTicker, String> { it.code }
        }

        return super.stringConverter(type, annotations, retrofit)
    }
}