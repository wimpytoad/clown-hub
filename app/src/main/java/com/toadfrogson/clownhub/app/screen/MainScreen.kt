package com.toadfrogson.clownhub.app.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toadfrogson.clownhub.app.viewmodel.MainViewModel
import com.toadfrogson.clownhub.app.viewmodel.UiEvents
import com.toadfrogson.clownhub.ui.components.ClownLoader
import com.toadfrogson.clownhub.ui.components.JokesList
import com.toadfrogson.clownhub.ui.components.TopBar
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel(), onThemeSwitched: () -> Unit) {
    LaunchedEffect(viewModel, Dispatchers.IO) {
        viewModel.loadContent()
    }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopBar(onThemeSwitched = {
            onThemeSwitched()
        }) },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val events = remember { viewModel.uiEvents }
        LaunchedEffect(events) {
            events.collect {
                if (it is UiEvents.SnackbarEvent) {
                    snackbarHostState.showSnackbar(message = it.message)
                }
            }
        }
        Column {
            Spacer(modifier = Modifier.height(60.dp))

            val state by viewModel.uiState.collectAsState()
            val isLoading = state.isLoading

            if (isLoading) {
                ClownLoader()
            } else {
                JokesList(content = state.content)
            }
        }


    }
}
