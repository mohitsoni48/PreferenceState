package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.floatPreferencesKey

class FloatPreferenceState(key: String, defaultValue: Float) : PreferenceState<Float>(
    key = floatPreferencesKey(key),
    defaultValue = defaultValue,
)

@Composable
fun rememberFloatPreferenceState(key: String, defaultValue: Float): PreferenceState<Float> {
    return remember(key) {
        FloatPreferenceState(key = key, defaultValue = defaultValue)
    }
}
