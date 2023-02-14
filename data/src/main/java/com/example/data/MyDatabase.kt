package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.CurrenciesItem

@Database([CurrenciesItem::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getDao(): SaveDao
}