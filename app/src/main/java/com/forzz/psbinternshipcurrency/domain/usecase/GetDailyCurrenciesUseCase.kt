package com.forzz.psbinternshipcurrency.domain.usecase

import com.forzz.psbinternshipcurrency.data.repository.CurrencyRepositoryImpl
import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies
import com.forzz.psbinternshipcurrency.domain.usecase.base.UseCase
import javax.inject.Inject

class GetDailyCurrenciesUseCase @Inject constructor(
    private val currencyRepositoryImpl: CurrencyRepositoryImpl
) : UseCase<DailyCurrencies>() {
    override suspend fun executeOnBackground(): DailyCurrencies {
        return currencyRepositoryImpl.getDailyData()
    }
}