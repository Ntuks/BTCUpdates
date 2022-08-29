package com.example.btcupdates.presentation.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun DashboardFooter(message: String) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )
        Text(
            text = "*$message",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}