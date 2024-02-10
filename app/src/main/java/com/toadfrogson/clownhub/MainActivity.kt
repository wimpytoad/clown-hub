package com.toadfrogson.clownhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.compose.ClownHubTheme
import com.toadfrogson.clownhub.app.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClownHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()

                }
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: MainViewModel = koinViewModel(),
) {
    LaunchedEffect(viewModel, Dispatchers.IO) {
        viewModel.loadContent()
    }
    Button(onClick = { /*TODO*/ }) {
    }
}
