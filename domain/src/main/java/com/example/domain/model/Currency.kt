package com.example.domain.model

data class Currency(
    val base: String,
    val date: Int,
    val rate: Double,
    val flag: String
)