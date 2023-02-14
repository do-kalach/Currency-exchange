package com.example.domain

import com.example.domain.model.Currency

class SaveFavouriteUseCase(private val repository: Repository) {
    suspend operator fun invoke(currency: Currency) {
        repository.saveFavourite(currency)
    }
}