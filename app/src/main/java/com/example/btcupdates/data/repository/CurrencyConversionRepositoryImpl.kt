package com.example.btcupdates.data.repository

import com.example.btcupdates.data.local.LocalCache
import com.example.btcupdates.data.remote.FixerAPIService
import com.example.btcupdates.domain.Result
import com.example.btcupdates.domain.models.*
import com.example.btcupdates.domain.repository.CurrencyConversionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyConversionRepositoryImpl @Inject constructor(
    private val apiService: FixerAPIService,
    private val localCache: LocalCache
): CurrencyConversionRepository {

    override suspend fun cacheCurrentValue(name: String, value: Any) {
        localCache.cacheValue(name, value)
    }

    override fun getCachedValue(name: String): Flow<Double?> {
        return localCache.getCachedValue(name)
    }

    override fun convertCurrencies(
        to: List<CURRENCY>,
        from: CURRENCY,
        amount: Double
    ): Flow<Result<ConversionData>> {
        return try {
            flow<Result.Success<ConversionData>> {
                to.forEach {
                    val conversionData = apiService.convertCurrency(it.value, from.value, amount)
                    emit(Result.Success(conversionData))
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            flow<Result.Error<ConversionData>> {
                Result.Error<ConversionData>(message = "Error getting conversion data.")
            }
        }
    }

    override suspend fun getCurrencyFluctuation(
        endDate: String,
        startDate: String
    ): Result<FluctuationData> {
        TODO("Not yet implemented")
    }

    override suspend fun getLatestExchangeRate(
        base: CURRENCY,
        symbols: List<CURRENCY>
    ): Result<LatestConversionData> {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoricalExchangeRate(
        endDate: String,
        startDate: String
    ): Result<HistoricalData> {
        TODO("Not yet implemented")
    }
}