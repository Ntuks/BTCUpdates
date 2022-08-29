package com.example.btcupdates.presentation.navigation

sealed class Screen(val route: String) {
    val bitcoinValuePathParam: String = "/{bitcoinCurrentValue}"

    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object History : Screen("history_screen")
}