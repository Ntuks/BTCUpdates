package com.example.btcupdates.presentation.welcome

sealed interface WelcomeScreenEvent {
    data class AddEditBitcoin(val amount: Double): WelcomeScreenEvent
    object SaveBitcoin: WelcomeScreenEvent
}
