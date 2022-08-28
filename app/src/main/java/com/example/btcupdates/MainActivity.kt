package com.example.btcupdates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btcupdates.presentation.navigation.BottomNavigationBar
import com.example.btcupdates.presentation.navigation.NavigationSetup
import com.example.btcupdates.presentation.navigation.TopBar
import com.example.btcupdates.ui.theme.BTCUpdatesTheme
import com.example.btcupdates.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTCUpdatesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    // Subscribe to navBackStackEntry, required to get current route
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    // State of bottomBar
                    val bottomNavBarState = rememberSaveable { mutableStateOf(false) }

                    // State of topBar
                    val currentRoute = navBackStackEntry?.destination?.route ?: stringResource(R.string.app_name)
                    val topBarState = rememberSaveable { mutableStateOf(Pair(false, currentRoute)) }

                    // Control TopBar and BottomBar
                    when (currentRoute) {
                        Screen.Welcome.route -> {
                            bottomNavBarState.value = false
                            topBarState.value = Pair(false, currentRoute)
                        }
                        Screen.Home.route -> {
                            bottomNavBarState.value = true
                            val screenName = stringResource(R.string.screen_title_home)
                            topBarState.value = Pair(true, screenName)
                        }
                        else -> {
                            bottomNavBarState.value = true
                            val screenName = stringResource(R.string.screen_title_history)
                            topBarState.value = Pair(true, screenName)
                        }
                    }

                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController, bottomNavBarState) },
                        topBar = { TopBar(topBarState) },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavigationSetup(navController = navController)
                    }
                }
            }
        }
    }
}