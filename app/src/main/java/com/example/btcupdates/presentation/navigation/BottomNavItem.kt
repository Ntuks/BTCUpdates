package com.example.btcupdates.presentation.navigation

import androidx.annotation.StringRes
import com.example.btcupdates.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        titleResId = R.string.screen_title_home,
    )

    object History : BottomNavItem(
        route = Screen.History.route,
        titleResId = R.string.screen_title_history,
    )
}