package com.mohitsoni48.preferencestate

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map

abstract class PreferenceState<T>(
    private val key: Preferences.Key<T>,
    private val defaultValue: T,
    private val dataStore: DataStore<Preferences> = _dataStore
) : MutableState<T> {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _state = mutableStateOf(defaultValue)

    override var value: T
        get() = _state.value
        set(value) {
            _state.value = value
            scope.launch {
                dataStore.edit { it[key] = value }
            }
        }

    init {
        scope.launch {
            dataStore.data.map { it[key] ?: defaultValue }
                .collect { newValue ->
                    withContext(Dispatchers.Main) { // Ensure recomposition happens on the main thread
                        _state.value = newValue
                    }
                }
        }
    }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = { value = it }

    companion object {
        private lateinit var _dataStore: DataStore<Preferences>
        fun initialize(context: Context, name: String) {
            _dataStore = providePreferenceDataStore(context, name)
        }
    }
}


internal fun providePreferenceDataStore(context: Context, name: String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { context.preferencesDataStoreFile(name) }
    )
}