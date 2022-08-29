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
            flow {
                to.forEach {
                    val response = apiService.convertCurrency(it.value, from.value, amount)

                    if (response.code() == 200) {
                        emit(Result.Success(response.body()))
                    } else {
                        val message = response.errorBody()?.charStream()?.readText()
                        Exception(message).printStackTrace()

                        emit(Result.Error(message = errorMessage))
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            flow<Result.Error<ConversionData>> {
                Result.Error<ConversionData>(message = errorMessage)
            }
        }
    }

    override fun getCurrencyFluctuation(
        endDate: String,
        startDate: String,
        base: CURRENCY,
        symbols: List<CURRENCY>
    ): Flow<Result<FluctuationData>> {
        return try {
            flow {
                val response = apiService.getCurrencyFluctuation(
                    endDate = endDate,
                    startDate = startDate,
                    base = base.name,
                    symbols = symbols.joinToString().replace(" ", "")
                )

                if (response.code() == 200) {
                    emit(Result.Success(response.body()))
                } else {
                    val message = response.errorBody()?.charStream()?.readText()
                    Exception(message).printStackTrace()

                    emit(Result.Error(message = errorMessage))
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            flow<Result.Error<FluctuationData>> {
                Result.Error<FluctuationData>(message = errorMessage)
            }
        }
    }

    companion object {
        private const val errorMessage = "Error getting data."
    }
}