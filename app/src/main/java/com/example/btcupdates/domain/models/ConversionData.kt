package com.example.btcupdates.domain.models

data class ConversionData(
    val date: String = "",
    val historical: String = "",
    val info: Info = Info(),
    val query: Query = Query(),
    val result: Double = 0.0,
    val success: Boolean = false
)

data class Info(
    val rate: Double = 0.0,
    val timestamp: Int = 0
)

data class Query(
    val amount: Double = 0.0,
    val from: String = "",
    val to: String = ""
)