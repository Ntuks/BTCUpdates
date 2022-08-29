package com.example.btcupdates.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btcupdates.domain.Result
import com.example.btcupdates.domain.models.CURRENCY.*
import com.example.btcupdates.domain.models.FluctuationData
import com.example.btcupdates.domain.repository.CurrencyConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor (
   private val repository: CurrencyConversionRepository
): ViewModel() {

    private var jobs: MutableMap<Int, Job?> = mutableMapOf()

    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> = _state

    var cachedValueFlow: Double? = null

    fun convertToFIATCurrencies() {
        _state.value = state.value.copy(isLoading = true, errorMessage = null)
        jobs[1]?.cancel()
        try {
            jobs[1] = repository.convertCurrencies(
                listOf(BTC, AUD, USD, ZAR), BTC, cachedValueFlow!!
            ).map { result ->
                _state.value = when (result) {
                    is Result.Error -> state.value.copy(
                        conversionData = state.value.conversionData,
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Success -> {
                        val updatedData = state.value.conversionData.toMutableList()
                        result.data?.let { data -> updatedData.add(data) }
                        state.value.copy(
                            conversionData = updatedData,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            }.launchIn(viewModelScope)
        } catch (ex: Exception) {
            ex.printStackTrace()
            _state.value = state.value.copy(
                isLoading = false,
                errorMessage = "Something went wrong"
            )
        }
    }

    fun getFluctuationData(startDate: String, endDate: String, isDailyFluctuation: Boolean = true) {
        _state.value = state.value.copy(isLoading = true, errorMessage = null)
        jobs[2]?.cancel()
        try {
            jobs[2] = repository.getCurrencyFluctuation(
                base = BTC,
                endDate = endDate,
                startDate = startDate,
                symbols = listOf(BTC, AUD, USD, ZAR),
            ).map { result ->
                _state.value = when (result) {
                    is Result.Error -> state.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                    is Result.Success -> {
                        val updatedData = result.data as FluctuationData
                        if (isDailyFluctuation) {
                            state.value.copy(
                                dailyFluctuationData = updatedData.rates,
                                isLoading = false,
                                errorMessage = null
                            )
                        } else {
                            state.value.copy(
                                thirtyDayFluctuationData = updatedData.rates,
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        } catch (ex: Exception) {
            ex.printStackTrace()
            _state.value = state.value.copy(
                isLoading = false,
                errorMessage = "Something went wrong"
            )
        }
    }

}