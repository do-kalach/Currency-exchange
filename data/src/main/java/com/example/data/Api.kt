package com.example.data

import com.example.data.model.Currencies
import com.example.data.model.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("currency")
    suspend fun getAllCurrencies(): Response<Currencies>

    @GET("currency/{base}")
    suspend fun getCurrencyData(@Path("base") base: String): Response<Currency>
}