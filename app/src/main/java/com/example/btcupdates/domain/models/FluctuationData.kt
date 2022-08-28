package com.example.btcupdates.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

data class FluctuationData(
    val base: String = "",
    @SerializedName("end_date")
    val endDate: String = "",
    @SerializedName("start_date")
    val startDate: String = "",
    val rates: Map<CURRENCY, RateFluctuation>,
    val success: Boolean = false,
    val fluctuation: Boolean = false,
)

@Serializable(with = CurrencyFluctuationSerializer::class)
sealed interface RateFluctuation {
    val change: Double
    val changPct: Double
    val endRate: Double
    val startRate: Double
}

@Serializable
data class Fluctuation (
    override val change: Double = 0.0,
    @SerializedName("change_pct")
    override val changPct: Double = 0.0,
    @SerializedName("end_rate")
    override val endRate: Double = 0.0,
    @SerializedName("start_rate")
    override val startRate: Double = 0.0
): RateFluctuation

object CurrencyFluctuationSerializer : JsonContentPolymorphicSerializer<RateFluctuation>(
    RateFluctuation::class
) {
    override fun selectDeserializer(element: JsonElement) = when {
        CURRENCY.BTC.value in element.jsonObject ||
                CURRENCY.USD.value in element.jsonObject ||
                CURRENCY.ZAR.value in element.jsonObject ||
                CURRENCY.AUD.value in element.jsonObject -> Fluctuation.serializer()
        else -> error("Unhandled currency type in $element")
    }
}