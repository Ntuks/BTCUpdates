package com.example.btcupdates.di

import com.example.btcupdates.data.repository.CurrencyConversionRepositoryImpl
import com.example.btcupdates.domain.repository.CurrencyConversionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCurrencyConversionRepository(
        repository: CurrencyConversionRepositoryImpl
    ) : CurrencyConversionRepository
}