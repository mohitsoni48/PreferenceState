package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

class DoublePreferenceState(key: String, defaultValue: Double) : PreferenceState<Double>(
    key = doublePreferencesKey(key),
    defaultValue = defaultValue,
)

@Composable
fun rememberDoublePreferenceState(key: String, defaultValue: Double): PreferenceState<Double> {
    return remember(key) {
        DoublePreferenceState(key = key, defaultValue = defaultValue)
    }
}
