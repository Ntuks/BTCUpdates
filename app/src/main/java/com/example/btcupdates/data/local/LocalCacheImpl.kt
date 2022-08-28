package com.example.btcupdates.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.btcupdates.domain.Result
import com.example.btcupdates.presentation.welcome.WelcomeViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val LOCAL_CACHE_NAME = "local_cache_name"

// Create a DataStore instance using the preferencesDataStore delegate, with the Context as
// receiver.
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = LOCAL_CACHE_NAME
)

class LocalCacheImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalCache {

    override suspend fun cacheValue(name: String, value: Any) {
        if (value is Double) context.dataStore.edit { preferences ->
            preferences[doublePreferencesKey(name)] = value
        }
    }

    override fun getCachedValue(name: String): Flow<Double?> {
        return context.dataStore.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[doublePreferencesKey(name)]
        }
    }
}