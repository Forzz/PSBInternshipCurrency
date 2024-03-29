package com.forzz.psbinternshipcurrency.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies
import com.forzz.psbinternshipcurrency.domain.usecase.GetDailyCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesScreenViewModel @Inject constructor(
    private val getDailyCurrenciesUseCase: GetDailyCurrenciesUseCase
) : ViewModel() {

    val dailyCurrencies = MutableLiveData<DailyCurrencies>()

    fun loadCurrencies() {
        getDailyCurrenciesUseCase.execute {
            onComplete { dailyCurrenciesResponse ->
                dailyCurrencies.postValue(dailyCurrenciesResponse)
            }
            onError {
                Log.d("CURRENCIES_RESPONSE", it.message.toString())
            }
        }
    }
}