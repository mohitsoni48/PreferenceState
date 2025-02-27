package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.stringPreferencesKey

@Composable
fun rememberStringPreferenceState(key: String, defaultValue: String): PreferenceState<String> {
    return remember {
        StringPreferenceState(key = key, defaultValue = defaultValue)
    }
}

class StringPreferenceState(key: String, defaultValue: String): PreferenceState<String>(
    key = stringPreferencesKey(key),
    defaultValue = defaultValue,
)