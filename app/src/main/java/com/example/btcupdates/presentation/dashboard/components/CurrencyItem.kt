package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.btcupdates.R
import com.example.btcupdates.domain.models.ConversionData
import com.example.btcupdates.domain.models.Fluctuation

@Composable
fun CurrencyItem(
    conversionData: ConversionData,
    fluctuationData: Fluctuation
) {
    val tintAndIcon = when {
        (fluctuationData.changePct > 0) -> {
            colorResource(R.color.green) to painterResource(R.drawable.north_east)
        }
        (fluctuationData.changePct < 0) -> {
            colorResource(R.color.red) to painterResource(R.drawable.south_west)
        }
        (fluctuationData.changePct == 0.0) -> {
            colorResource(R.color.white) to painterResource(R.drawable.trending_flat)
        }
        else -> colorResource(R.color.white) to painterResource(R.drawable.sync_problem)
    }

    Row(
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment= Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "${conversionData.query.to}: ",
            modifier = Modifier.padding(end = 40.dp)
        )
        Text(
            text = conversionData.result.toString(),
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Icon(
            painter = tintAndIcon.second,
            tint = tintAndIcon.first,
            contentDescription = null
        )
    }
}