package com.example.currency_exchange.di

import com.example.data.Api
import com.example.data.RepositoryImpl
import com.example.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Api {
        return Retrofit.Builder()
            .baseUrl("https://us-central1-epam-laba-13-1527598553135.cloudfunctions.net/myWebsiteBackend/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideMainRepositoryImpl(api: Api): Repository {
        return RepositoryImpl(api = api)
    }

    private fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideInterceptor())
            .build()
    }
}