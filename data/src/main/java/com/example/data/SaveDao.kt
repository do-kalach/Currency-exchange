package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.model.Currency

@Dao
interface SaveDao {

    @Insert
    suspend fun insert(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    @Query("SELECT * FROM favourite_currency")
    fun getFavCurrency(): Currency

}