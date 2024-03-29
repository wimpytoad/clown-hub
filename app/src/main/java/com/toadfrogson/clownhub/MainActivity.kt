package com.toadfrogson.clownhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.toadfrogson.clownhub.ui.screen.MainScreen
import com.toadfrogson.clownhub.ui.theme.ClownHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var appTheme by remember { mutableStateOf(false) }
            ClownHubTheme(useDarkTheme = appTheme) {
                MainScreen {
                    appTheme = !appTheme
                }
            }
        }
    }
}
