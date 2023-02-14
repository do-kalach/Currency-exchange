package com.example.data.model

import androidx.room.Entity

@Entity("favourite_currency")
data class CurrenciesItem(
    val base: String,
    val date: Int,
    val rate: Double
)