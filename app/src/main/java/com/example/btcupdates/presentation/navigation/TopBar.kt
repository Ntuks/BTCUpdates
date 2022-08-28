package com.example.btcupdates.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember

@Composable
fun TopBar(state: MutableState<Pair<Boolean, String>>) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = state.value.first
    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = { Text(text = state.value.second) },
            )
        }
    )
}