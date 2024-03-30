package com.forzz.psbinternshipcurrency.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.forzz.psbinternshipcurrency.domain.model.DailyCurrencies
import com.forzz.psbinternshipcurrency.domain.usecase.GetDailyCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.forzz.psbinternshipcurrency.utils.Constants
import com.forzz.psbinternshipcurrency.utils.Extensions.Companion.convertToCustomDateString
import kotlinx.coroutines.cancel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CurrenciesScreenViewModel @Inject constructor(
    private val getDailyCurrenciesUseCase: GetDailyCurrenciesUseCase
) : ViewModel() {

    private val _dailyCurrencies = MutableLiveData<DailyCurrencies>()
    val dailyCurrencies: LiveData<DailyCurrencies> = _dailyCurrencies

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _lastSuccessUpdateDateText = MutableLiveData<String>()
    val lastSuccessUpdateDateText: LiveData<String> = _lastSuccessUpdateDateText

    private val _lastCbrUpdateDateText = MutableLiveData<String>()
    val lastCbrUpdateDateText: LiveData<String> = _lastCbrUpdateDateText

    init {
        startDataUpdates()
    }

    private fun startDataUpdates() {
        viewModelScope.launch {
            while (true) {
                loadCurrencies()
                delay(Constants.DATA_UPDATE_INTERVAL)
            }
        }
    }

    fun loadCurrencies() {
        _isLoading.postValue(true)
        getDailyCurrenciesUseCase.execute {
            onComplete { dailyCurrenciesResponse ->
                _dailyCurrencies.postValue(dailyCurrenciesResponse)
                _isLoading.postValue(false)
                _lastCbrUpdateDateText.postValue("Данные ЦБ на ${dailyCurrenciesResponse.date.convertToCustomDateString()}")
                _lastSuccessUpdateDateText.postValue("Последнее обновление: ${Date().convertToCustomDateString()}")
            }
            onError { error ->
                _errorMessage.postValue("Ошибка сети: ${error.message}")
                _isLoading.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}