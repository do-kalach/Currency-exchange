package com.example.domain

import com.example.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAllCurrencies(): ResultOf

    suspend fun getCurrencyData(base: String): Flow<Currency>

}