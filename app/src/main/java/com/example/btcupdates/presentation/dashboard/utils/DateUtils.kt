package com.example.btcupdates.presentation.dashboard.utils

import java.text.SimpleDateFormat
import java.util.*

fun getStartAndEndDate(period: Int, amount: Int = -1): Pair<String, String> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = Date()

    val endDate = dateFormat.format(calendar.time)

    calendar.add(period, amount)
    val startDate = dateFormat.format(calendar.time)

    return startDate to endDate
}