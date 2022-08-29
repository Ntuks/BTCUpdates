package com.example.btcupdates.presentation.dashboard

import com.example.btcupdates.domain.models.ConversionData
import com.example.btcupdates.domain.models.Fluctuation

data class DashboardState(
    val conversionData: List<ConversionData> = listOf(),
    val dailyFluctuationData: Map<String, Fluctuation> = mutableMapOf(),
    val thirtyDayFluctuationData: Map<String, Fluctuation> = mutableMapOf(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)