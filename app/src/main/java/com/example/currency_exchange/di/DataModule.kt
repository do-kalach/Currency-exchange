package com.example.currency_exchange.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "favourite.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}