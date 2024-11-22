package com.frenkel.finnhub_client.interceptors

import okhttp3.Interceptor
import okhttp3.Response

internal class FinnhubApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .headers(
                    chain.request().headers.newBuilder()
                        .add("X-Finnhub-Token", apiKey)
                        .build()
                )
                .build()

        return chain.proceed(request)
    }
}