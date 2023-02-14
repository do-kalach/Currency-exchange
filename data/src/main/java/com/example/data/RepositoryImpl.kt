package com.example.data

import com.example.domain.Repository
import com.example.domain.ResultOf
import com.example.domain.model.Currency
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class RepositoryImpl(private val api: Api, private val database: MyDatabase) :
    Repository {

    override suspend fun getAllCurrencies(): ResultOf {
        var result: ResultOf? = null
        try {
            if (api.getAllCurrencies().isSuccessful) {
                val data = api.getAllCurrencies().body()!!.map {
                    Currency(
                        base = it.base,
                        date = it.date,
                        rate = it.rate,
                        flag = "ic_${it.base.toLowerCase()}_flag"
                    )
                }
                result = ResultOf.Success(data)
            }
        } catch (e: HttpException) {
            result = ResultOf.Error(e)
        }
        return result!!
    }

    override suspend fun getCurrencyData(base: String): Flow<Currency> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavourite(currency: Currency) {

    }

}