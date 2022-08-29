package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.btcupdates.R
import com.example.btcupdates.domain.models.ConversionData
import com.example.btcupdates.domain.models.Fluctuation
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToLong

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

    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.DOWN

    Row(
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment= Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "${conversionData.query.to}: ",
            modifier = Modifier.padding(end = 50.dp)
        )
        Text(
            text = df.format(conversionData.result).toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Text(
            text = df.format(fluctuationData.changePct).toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            painter = tintAndIcon.second,
            tint = tintAndIcon.first,
            contentDescription = null
        )
    }
}