package com.example.btcupdates.di

import com.example.btcupdates.BuildConfig
import com.example.btcupdates.data.remote.FixerAPIService
import com.example.btcupdates.data.remote.HeadersInterceptor
import com.example.btcupdates.data.remote.loggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HeadersInterceptor())
        addInterceptor(loggingInterceptor)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder().apply {
        baseUrl(BuildConfig.API_HOST_URL)
        client(provideOkHttpClient())
        addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideEventsApi(): FixerAPIService = provideRetrofitBuilder().build().create()

}