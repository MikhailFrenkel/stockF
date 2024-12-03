package com.frenkel.polygon_client.interceptors

import okhttp3.Interceptor
import okhttp3.Response

data class PolygonApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .headers(
                    chain.request().headers.newBuilder()
                        .add("Authorization", "Bearer $apiKey")
                        .build()
                )
                .build()

        return chain.proceed(request)
    }
}
