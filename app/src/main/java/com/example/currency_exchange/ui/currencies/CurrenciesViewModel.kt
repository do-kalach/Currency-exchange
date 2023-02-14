package com.example.currency_exchange.ui.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.CurrenciesUseCase
import com.example.domain.Repository
import com.example.domain.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(repository: Repository) : ViewModel() {

    private val useCase = CurrenciesUseCase(repository)
    val data = MutableStateFlow<ResultOf>(ResultOf.Loading)

    fun getAllCurrencies() {
        viewModelScope.launch {
            repeat(5) {
                data.emit(useCase.invoke())
            }
        }
    }

    fun sortList() {

    }
}