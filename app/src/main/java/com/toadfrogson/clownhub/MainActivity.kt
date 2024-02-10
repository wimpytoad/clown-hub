package com.toadfrogson.clownhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.ClownHubTheme
import com.toadfrogson.clownhub.app.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClownHubTheme {
                MainScreen()
            }
        }
    }
}
