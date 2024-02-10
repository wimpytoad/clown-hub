package com.toadfrogson.clownhub.app.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.toadfrogson.clownhub.app.viewmodel.MainViewModel
import com.toadfrogson.clownhub.ui.components.ClownLoader
import com.toadfrogson.clownhub.ui.components.JokesList
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    LaunchedEffect(viewModel, Dispatchers.IO) {
        viewModel.loadContent()
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state by viewModel.uiState.collectAsState()
        val isLoading = state.isLoading

        if (isLoading) {
            ClownLoader()
        } else {
            JokesList(content = state.content)
        }
    }
}