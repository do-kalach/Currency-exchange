package com.example.domain

import com.example.domain.model.Currency

sealed class ResultOf {
    class Success(val data: List<Currency>) : ResultOf()
    class Error(val e: Throwable) : ResultOf()
    object Loading : ResultOf()
}