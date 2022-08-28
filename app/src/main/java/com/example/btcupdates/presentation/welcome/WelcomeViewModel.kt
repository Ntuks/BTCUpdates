package com.example.btcupdates.presentation.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btcupdates.domain.repository.CurrencyConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor (
   private val repository: CurrencyConversionRepository
): ViewModel() {

    var state by mutableStateOf(WelcomeState())
        private set

    val cachedValueFlow: Flow<Double?>
        get() = repository.getCachedValue(BITCOIN_PROTO_DATA_STORE_KEY)


    fun onEvent(event: WelcomeScreenEvent) {
        when (event) {
            is WelcomeScreenEvent.AddEditBitcoin -> state = state.copy(amount = event.amount)
            WelcomeScreenEvent.SaveBitcoin -> {
                viewModelScope.launch{
                    try {
                        repository.cacheCurrentValue(
                            BITCOIN_PROTO_DATA_STORE_KEY,
                            state.amount as Double
                        )
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                }
            }
        }
    }

    companion object {
        const val BITCOIN_PROTO_DATA_STORE_KEY = "current_btc_value"
    }
}