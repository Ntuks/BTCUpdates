package com.example.btcupdates.data.remote

import com.example.btcupdates.domain.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val basePath: String = "/fixer"

interface FixerAPIService {

    @GET("$basePath/convert")
    suspend fun convertCurrency(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double,
    ): Response<ConversionData>

    @GET("$basePath/fluctuation")
    suspend fun getCurrencyFluctuation(
        @Query("end_date") endDate: String,
        @Query("start_date") startDate: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): Response<FluctuationData>
}