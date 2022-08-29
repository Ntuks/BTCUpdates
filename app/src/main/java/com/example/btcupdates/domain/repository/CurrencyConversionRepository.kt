package com.example.btcupdates.domain.repository

import com.example.btcupdates.domain.models.CURRENCY
import com.example.btcupdates.domain.models.ConversionData
import com.example.btcupdates.domain.models.FluctuationData
import com.example.btcupdates.domain.Result
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionRepository {

    fun getCachedValue(name: String): Flow<Double?>

    suspend fun cacheCurrentValue(name: String, value: Any)

    /** */
    fun convertCurrencies(
        to: List<CURRENCY>,
        from: CURRENCY, amount: Double
    ): Flow<Result<ConversionData>>

    /** */
    fun getCurrencyFluctuation(
        endDate: String,
        startDate: String,
        base: CURRENCY,
        symbols: List<CURRENCY>
    ): Flow<Result<FluctuationData>>
}