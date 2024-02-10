package com.toadfrogson.clownhub.app.viewmodel

import androidx.lifecycle.ViewModel
import com.toadfrogson.clownhub.data.repo.JokesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainState(val isLoading: Boolean = false)

class MainViewModel(val repo: JokesRepo): ViewModel() {
    private val privateUiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = privateUiState.asStateFlow()

    suspend fun loadContent() {
        repo.fetchJokes()
    }
}