package com.example.btcupdates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btcupdates.presentation.dashboard.home.HomeScreen
import com.example.btcupdates.presentation.welcome.WelcomeScreen
import com.example.btcupdates.presentation.welcome.WelcomeViewModel

@Composable
fun NavigationSetup(navController: NavHostController) {

    val welcomeViewModel: WelcomeViewModel = hiltViewModel()

    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController, welcomeViewModel)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController, welcomeViewModel)
        }
        composable(Screen.History.route) {
//            EventVideoScreen(eventsViewModel)
        }
    }
}