package com.example.domain

class CurrencyUseCase(private val repository: Repository) {
    suspend operator fun invoke(base: String) {
        repository.getCurrencyData(base)
    }
}