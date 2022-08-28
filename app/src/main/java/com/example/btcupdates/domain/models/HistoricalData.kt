package com.example.btcupdates.domain.models

import com.google.gson.annotations.SerializedName

data class HistoricalData(
    val base: String,
    @SerializedName("end_date")
    val endDate: String = "",
    @SerializedName("start_date")
    val rates: Map<CURRENCY, Double>,
    val success: Boolean,
    @SerializedName("time_series")
    val timeSeries: Boolean
)