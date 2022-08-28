package com.example.btcupdates.presentation.dashboard.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.btcupdates.R
import com.example.btcupdates.presentation.welcome.WelcomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val text = remember { mutableStateOf("${state.amount ?: ""}")}

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
                text = stringResource(state.bitcoinMessageId),
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
                        contentDescription = null
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    text.value = it
//                    if (it.isNotEmpty() && it.isDigitsOnly())
//                        viewModel.onEvent(WelcomeScreenEvent.AddEditBitcoin(it.toDouble()))
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 120.dp, end = 16.dp),
                onClick = {
//                        navController.navigate(Screen.Home.route) {
//                            navController.graph.startDestinationRoute?.let { route ->
//                                popUpTo(route) {
//                                    saveState = true
//                                }
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
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
}