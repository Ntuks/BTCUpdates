package com.example.btcupdates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.btcupdates.presentation.dashboard.DashboardViewModel
import com.example.btcupdates.presentation.dashboard.history.HistoryScreen
import com.example.btcupdates.presentation.dashboard.home.HomeScreen
import com.example.btcupdates.presentation.utils.Constants.Companion.BITCOIN_VALUE_PARAM_NAME
import com.example.btcupdates.presentation.welcome.WelcomeScreen
import com.example.btcupdates.presentation.welcome.WelcomeViewModel

@Composable
fun NavigationSetup(navController: NavHostController) {

    val welcomeViewModel: WelcomeViewModel = hiltViewModel()
    val dashboardViewModel : DashboardViewModel = hiltViewModel()

    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController, welcomeViewModel)
        }
        composable(
            route = Screen.Home.route + Screen.Home.bitcoinValuePathParam,
            arguments = listOf(navArgument( BITCOIN_VALUE_PARAM_NAME) { type = NavType.FloatType})
        ) { backStackEntry ->
            backStackEntry.arguments?.getFloat(BITCOIN_VALUE_PARAM_NAME)?.let {
                HomeScreen(it, dashboardViewModel)
            }
        }
        composable(
            route = Screen.History.route + Screen.History.bitcoinValuePathParam,
            arguments = listOf(navArgument( BITCOIN_VALUE_PARAM_NAME) { type = NavType.FloatType})
        ) { backStackEntry ->
            backStackEntry.arguments?.getFloat(BITCOIN_VALUE_PARAM_NAME)?.let {
                HistoryScreen(it, dashboardViewModel)
            }
        }
    }
}