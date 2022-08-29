package com.example.btcupdates.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.btcupdates.R
import com.example.btcupdates.presentation.utils.Constants

@Composable
fun BottomNavigationBar(
    navController: NavController,
    state: MutableState<Boolean>
) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.History)
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = state.value

    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    val icon: @Composable () -> Unit = when (item) {
                        BottomNavItem.History -> {
                            {
                                Icon(
                                    painter = painterResource(R.drawable.list_bulleted),
                                    contentDescription = stringResource(id = item.titleResId)
                                )
                            }
                        }
                        BottomNavItem.Home -> {
                            {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = stringResource(id = item.titleResId)
                                )
                            }
                        }
                    }

                    BottomNavigationItem(
                        icon = icon,
                        label = { Text(text = stringResource(id = item.titleResId)) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.currentBackStackEntry?.let { stack ->
                                stack.arguments?.getFloat(Constants.BITCOIN_VALUE_PARAM_NAME)?.let {
                                    navController.navigate("${item.route}/${it}") {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route)
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}