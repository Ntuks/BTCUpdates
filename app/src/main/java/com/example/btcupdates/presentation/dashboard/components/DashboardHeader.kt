package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.btcupdates.R

@Composable
fun DashboardHeader(
    title: String,
    bitcoinCurrentValue: String,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface,
            maxLines = 2,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.currency_bitcoin),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 1.dp)
            )
            Text(
                style = MaterialTheme.typography.h6,
                text = bitcoinCurrentValue,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                modifier = Modifier.padding(start = 2.dp, top = 1.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 30.dp),
            verticalAlignment= Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = stringResource(R.string.currency),
                modifier = Modifier.padding(end = 30.dp)
            )
            Text(
                text = stringResource(R.string.value),
                modifier = Modifier.weight(1f)
            )
            Text(text = "*${stringResource(R.string.indicator)}")
        }
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
                .padding(top = 16.dp)
        )
    }
}