package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.intPreferencesKey

class IntPreferenceState(key: String, defaultValue: Int) : PreferenceState<Int>(
    key = intPreferencesKey(key),
    defaultValue = defaultValue,
)

@Composable
fun rememberIntPreferenceState(key: String, defaultValue: Int): PreferenceState<Int> {
    return remember(key) {
        IntPreferenceState(key = key, defaultValue = defaultValue)
    }
}
