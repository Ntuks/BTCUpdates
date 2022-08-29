package com.example.btcupdates.domain.models

import com.google.gson.annotations.SerializedName

data class FluctuationData(
    var base: String = "",
    @SerializedName("end_date")
    var endDate: String = "",
    @SerializedName("start_date")
    var startDate: String = "",
    var rates: Map<String, Fluctuation> = mutableMapOf(),
    var success: Boolean = false,
    var fluctuation: Boolean = false,
)

data class Fluctuation (
    val change: Double = 0.0,
    @SerializedName("change_pct")
    var changePct: Double = 0.0,
    @SerializedName("end_rate")
    var endRate: Double = 0.0,
    @SerializedName("start_rate")
    var startRate: Double = 0.0
)