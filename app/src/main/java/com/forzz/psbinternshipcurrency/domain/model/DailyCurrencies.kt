package com.forzz.psbinternshipcurrency.domain.model

import java.util.Date

data class DailyCurrencies(
    val date: Date,
    val previousDate: Date,
    val currencies: List<Currency>
)
