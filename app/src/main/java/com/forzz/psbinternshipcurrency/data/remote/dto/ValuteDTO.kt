package com.forzz.psbinternshipcurrency.data.remote.dto

import com.forzz.psbinternshipcurrency.domain.model.Currency
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class ValuteDTO(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previous: Double
) {
    fun toCurrency(): Currency {
        return Currency(id,  numCode, charCode, nominal, name, value.toFloat(), previous.toFloat())
    }
}
