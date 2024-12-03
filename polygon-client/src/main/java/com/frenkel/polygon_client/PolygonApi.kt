package com.frenkel.polygon_client

import com.frenkel.polygon_client.converters.DateConverterFactory
import com.frenkel.polygon_client.interceptors.PolygonApiKeyInterceptor
import com.frenkel.polygon_client.models.AggregatesResponse
import com.frenkel.polygon_client.models.Sort
import com.frenkel.polygon_client.models.Timespan
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface PolygonApi {

    @GET("/v2/aggs/ticker/{stocksTicker}/range/{multiplier}/{timespan}/{from}/{to}")
    suspend fun fetchAggregates(
        @Path("stocksTicker") stocksTicker: String,
        @Path("multiplier") multiplier: Int,
        @Path("timespan") timespan: Timespan,
        @Path("from") from: Date,
        @Path("to") to: Date,
        @Query("adjusted") adjusted: Boolean = false,
        @Query("sort") sort: Sort = Sort.ASC,
    ): Result<AggregatesResponse>
}

fun BuildPolygonApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): PolygonApi {
    val jsonConverterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(PolygonApiKeyInterceptor(apiKey))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(DateConverterFactory())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()

    return retrofit.create()
}