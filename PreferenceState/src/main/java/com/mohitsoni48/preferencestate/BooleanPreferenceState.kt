package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.booleanPreferencesKey

class BooleanPreferenceState(key: String, defaultValue: Boolean) : PreferenceState<Boolean>(
    key = booleanPreferencesKey(key),
    defaultValue = defaultValue,
)

@Composable
fun rememberBooleanPreferenceState(key: String, defaultValue: Boolean): PreferenceState<Boolean> {
    return remember(key) {
        BooleanPreferenceState(key = key, defaultValue = defaultValue)
    }
}
