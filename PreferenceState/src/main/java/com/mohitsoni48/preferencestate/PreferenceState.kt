package com.mohitsoni48.preferencestate

import androidx.compose.runtime.MutableState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

abstract class PreferenceState<T>(
    private val key: Preferences.Key<T>,
    private val defaultValue: T,
    private val dataStore: DataStore<Preferences>
): MutableState<T> {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _state = MutableStateFlow(defaultValue)

    override var value
        get() = _state.value
        set(value) {
            set(value)
        }

    init {
        scope.launch {
            dataStore.data.map { it[key] ?: defaultValue }
                .collect { _state.value = it }
        }
    }

    fun set(value: T) {
        _state.value = value
        scope.launch {
            dataStore.edit { it[key] = value }
        }
    }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = {
        value = it
    }
}