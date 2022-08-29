package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.btcupdates.R

@Composable
fun CustomSnackBar(
    modifier: Modifier,
    state: MutableState<SnackbarHostState>
) {
    SnackbarHost(
        modifier = modifier,
        hostState = state.value,
        snackbar = {
            Snackbar(
                action = {
                    TextButton(onClick = { state.value.currentSnackbarData?.dismiss() }) {
                        Text(
                            text = it.actionLabel as String,
                            color = colorResource(R.color.black),
                        )
                    }
                }
            ) {
                Text(text = it.message, color = colorResource(R.color.red))
            }
        }
    )
}