package com.forzz.psbinternshipcurrency.domain.repository

import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies

interface CurrencyRepository {

    suspend fun getDailyData(): DailyCurrencies
}