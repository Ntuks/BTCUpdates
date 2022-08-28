package com.example.btcupdates.data.remote

import com.example.btcupdates.domain.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerAPIService {

    @GET("/convert?to={to}&from={from}&amount={amount}")
    suspend fun convertCurrency(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double,
    ): ConversionData

    @GET("/fluctuation?start_date={start_date}&end_date={end_date}")
    suspend fun getCurrencyFluctuation(
        @Query("end_date") endDate: String,
        @Query("start_date") startDate: String,
    ): FluctuationData

    @GET("/latest?symbols={symbols}&base={base}")
    suspend fun getLatestExchangeRate(
        @Query("base") base: CURRENCY,
        @Query("symbols") symbols: String,
    ): LatestConversionData

    @GET("/timeseries?start_date={start_date}&end_date={end_date}")
    suspend fun getHistoricalExchangeRate(
        @Query("end_date") endDate: String,
        @Query("start_date") startDate: String,
    ): HistoricalData

}