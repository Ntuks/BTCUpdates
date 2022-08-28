package com.example.btcupdates.domain.models

data class LatestConversionData(
    val base: String,
    val date: String,
    val rates: Map<CURRENCY, Double>,
    val success: Boolean,
    val timestamp: Long,
)