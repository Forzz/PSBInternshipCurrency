package com.forzz.psbinternshipcurrency.data.repository

import com.forzz.psbinternshipcurrency.data.remote.CbrCurrencyApi
import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies
import com.forzz.psbinternshipcurrency.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CbrCurrencyApi
) : CurrencyRepository {
    override suspend fun getDailyData(): DailyCurrencies {
        return api.getDailyData().toDailyCurrencies()
    }
}