package com.example.domain

class CurrenciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(): ResultOf {
        return repository.getAllCurrencies()
    }
}