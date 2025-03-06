package com.mohitsoni48.preferencestate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.longPreferencesKey

class LongPreferenceState(key: String, defaultValue: Long) : PreferenceState<Long>(
    key = longPreferencesKey(key),
    defaultValue = defaultValue,
)

@Composable
fun rememberLongPreferenceState(key: String, defaultValue: Long): PreferenceState<Long> {
    return remember(key) {
        LongPreferenceState(key = key, defaultValue = defaultValue)
    }
}
