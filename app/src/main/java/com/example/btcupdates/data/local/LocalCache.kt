package com.example.btcupdates.data.local

import kotlinx.coroutines.flow.Flow


interface LocalCache {
    fun getCachedValue(name: String): Flow<Double?>
    suspend fun cacheValue(name: String, value: Any)
}