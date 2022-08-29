package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.btcupdates.domain.models.ConversionData
import com.example.btcupdates.domain.models.Fluctuation

@Composable
fun DashboardList(
    conversionData: List<ConversionData>,
    fluctuationData: Map<String, Fluctuation>
) {
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, top = 20.dp, end = 16.dp),
    ) {
        items(conversionData) { currencyData: ConversionData ->
            fluctuationData[currencyData.query.to]?.let{ currencyFluctuation ->
                CurrencyItem(
                    conversionData = currencyData,
                    fluctuationData = currencyFluctuation
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}