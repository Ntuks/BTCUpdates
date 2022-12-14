package com.example.btcupdates.presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.btcupdates.R
import com.example.btcupdates.presentation.navigation.Screen

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel
) {
    val state = viewModel.state
    val cachedValueState = viewModel.cachedValueFlow.collectAsState(null)

    val text = remember{ mutableStateOf("") }
    val inputIsInValid = remember{ mutableStateOf(false) }

    var bitcoinMessageId = state.bitcoinMessageId
    if (state.amount == null && cachedValueState.value != null) {
        text.value = cachedValueState.value.toString()
        bitcoinMessageId = R.string.update_bitcoin_value
        viewModel.onEvent(WelcomeScreenEvent.AddEditBitcoin(cachedValueState.value!!))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.h2,
            text = stringResource(R.string.welcome),
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            style = MaterialTheme.typography.h4,
            text = stringResource(bitcoinMessageId),
            color = MaterialTheme.colors.onSurface,
            maxLines = 3,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = text.value,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.currency_bitcoin),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            maxLines = 1,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            isError = inputIsInValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                text.value = it.trimEnd()
                if (text.value.isNotEmpty()) {
                    if (Regex("^[0-9_.-]*\$").matches(text.value)){
                        if (text.value != state.amount.toString())
                            viewModel.onEvent(WelcomeScreenEvent.AddEditBitcoin(it.toDouble()))

                        inputIsInValid.value = false
                    } else {
                        inputIsInValid.value = true
                    }
                }
            }
        )

        Button(
            enabled = state.amount != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 120.dp, end = 16.dp),
            onClick = {
                viewModel.onEvent(WelcomeScreenEvent.SaveBitcoin)
                navController.navigate("${Screen.Home.route}/${text.value}") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Text(
                text = stringResource(R.string.done),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(2.dp),
            )
        }
    }

}