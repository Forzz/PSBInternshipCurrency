package com.forzz.psbinternshipcurrency.data.remote.dto

import com.forzz.psbinternshipcurrency.domain.model.Currency
import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class DailyResponseDTO(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: Map<String, ValuteDTO>
) {
    fun toDailyCurrencies(): DailyCurrencies {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
        val currencies: List<Currency> = valute.map { (_, value) -> value.toCurrency() }

        return DailyCurrencies(
            formatter.parse(date) ?: Date(),
            formatter.parse(previousDate) ?: Date(),
            currencies
        )
    }
}
