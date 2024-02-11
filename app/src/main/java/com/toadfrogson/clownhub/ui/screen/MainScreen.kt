package com.toadfrogson.clownhub.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.toadfrogson.clownhub.R
import com.toadfrogson.clownhub.presentation.viewmodel.MainViewModel
import com.toadfrogson.clownhub.ui.components.ClownLoader
import com.toadfrogson.clownhub.ui.components.JokesList
import com.toadfrogson.clownhub.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel(), onThemeSwitched: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopBar(onThemeSwitched = {
                onThemeSwitched()
            })
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val events = remember { viewModel.uiEvents }
        val snackbarErrorText = stringResource(R.string.error_snackbar_text)
        LaunchedEffect(events) {
            events.collect {
                snackbarHostState.showSnackbar(message = snackbarErrorText)
            }
        }

        Column {
            Spacer(modifier = Modifier.height(60.dp))

            val state by viewModel.uiState.collectAsState()
            val isLoading = state.isLoading
            AnimatedVisibility(
                visible = isLoading || state.content.isEmpty(),
                enter = expandIn(),
                exit = shrinkOut()
            ) {
                Column {
                    ClownLoader()
                    if(state.content.isEmpty()) {
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            text = stringResource(R.string.no_jokes_found_text),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = !isLoading && state.content.isNotEmpty(),
                enter = expandIn(),
                exit = shrinkOut()
            ) {
                JokesList(content = state.content)
            }
        }
    }
}
