package com.example.btcupdates.presentation.dashboard.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.btcupdates.R
import com.example.btcupdates.presentation.dashboard.DashboardViewModel
import com.example.btcupdates.presentation.dashboard.components.CustomSnackBar
import com.example.btcupdates.presentation.dashboard.components.DashboardFooter
import com.example.btcupdates.presentation.dashboard.components.DashboardHeader
import com.example.btcupdates.presentation.dashboard.components.DashboardList
import com.example.btcupdates.presentation.dashboard.utils.getStartAndEndDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun HistoryScreen(
    bitcoinCurrentValue: Float,
    viewModel: DashboardViewModel
) {
    val state = viewModel.state.value
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }

    if (state.thirtyDayFluctuationData.isEmpty() && state.errorMessage == null) {
        viewModel.cachedValueFlow = bitcoinCurrentValue.toDouble()

        val (startDate, endDate) = getStartAndEndDate(Calendar.MONTH)
        viewModel.getFluctuationData(startDate, endDate, false)
    }

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val ref = createRef()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DashboardHeader(
                title = stringResource(R.string.history_screen_description),
                bitcoinCurrentValue = bitcoinCurrentValue.toString()
            )
            if (state.isLoading){
                CircularProgressIndicator()
            } else {
                DashboardList(state.conversionData, state.thirtyDayFluctuationData)
            }
            DashboardFooter(stringResource(R.string.thirty_day_indicator))
        }

        if(!state.errorMessage.isNullOrEmpty()) {
            coroutineScope.launch {
                snackBarHostState.value.showSnackbar(
                    message = state.errorMessage,
                    actionLabel = "Hide",
                    duration = SnackbarDuration.Long
                )
            }
            CustomSnackBar(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .constrainAs(ref) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    }
                ,
                state = snackBarHostState
            )
        }
    }
}