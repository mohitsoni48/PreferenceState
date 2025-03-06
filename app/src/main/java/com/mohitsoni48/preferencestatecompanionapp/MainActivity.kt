package com.mohitsoni48.preferencestatecompanionapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohitsoni48.preferencestate.PreferenceState
import com.mohitsoni48.preferencestate.rememberStringPreferenceState
import com.mohitsoni48.preferencestatecompanionapp.ui.theme.PreferenceStateCompanionAppTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceState.initialize(this, "settings")
        enableEdgeToEdge()
        setContent {
            PreferenceStateCompanionAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var randomString by rememberStringPreferenceState("randomString", "Hello World")

    Text(
        text = randomString,
        modifier = modifier.clickable {
            randomString = UUID.randomUUID().toString()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PreferenceStateCompanionAppTheme {
        Greeting()
    }
}