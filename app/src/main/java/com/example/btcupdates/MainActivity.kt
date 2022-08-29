package com.example.btcupdates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.btcupdates.presentation.navigation.BottomNavigationBar
import com.example.btcupdates.presentation.navigation.NavigationSetup
import com.example.btcupdates.presentation.navigation.Screen
import com.example.btcupdates.presentation.navigation.TopBar
import com.example.btcupdates.ui.theme.BTCUpdatesTheme
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

                    val topBarState = rememberSaveable { mutableStateOf(Pair(false, 0)) }
                    val bottomNavBarState = rememberSaveable { mutableStateOf(false) }

                    val navController = rememberNavController()
                    navController.addOnDestinationChangedListener{ _, dest, _ ->
                        when (dest.route?.substringBefore("/")) {
                            Screen.Welcome.route -> {
                                bottomNavBarState.value = false
                                topBarState.value = Pair(false, 0)
                            }
                            Screen.Home.route -> {
                                bottomNavBarState.value = true
                                topBarState.value = Pair(true, R.string.screen_title_home)
                            }
                            Screen.History.route -> {
                                bottomNavBarState.value = true
                                topBarState.value = Pair(true, R.string.screen_title_history)
                            }
                            else -> Unit
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