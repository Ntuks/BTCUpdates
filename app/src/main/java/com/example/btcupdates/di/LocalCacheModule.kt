package com.example.btcupdates.di

import com.example.btcupdates.data.local.LocalCache
import com.example.btcupdates.data.local.LocalCacheImpl
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
abstract class LocalCacheModule {

    @Binds
    @Singleton
    abstract fun provideLocalCacheRepository(repository: LocalCacheImpl): LocalCache
}