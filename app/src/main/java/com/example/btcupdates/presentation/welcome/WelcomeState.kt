package com.example.btcupdates.presentation.welcome

import com.example.btcupdates.R

data class WelcomeState(
    val bitcoinMessageId: Int = R.string.enter_bitcoin_value,
    val amount: Double? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)