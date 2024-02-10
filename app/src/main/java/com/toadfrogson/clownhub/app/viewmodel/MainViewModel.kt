package com.toadfrogson.clownhub.app.viewmodel

import androidx.lifecycle.ViewModel
import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.repo.JokesRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainState(val isLoading: Boolean = false, val content: List<JokeModel> = emptyList())

class MainViewModel(private val repo: JokesRepo): ViewModel() {
    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: SharedFlow<UiEvents> = _uiEvents.asSharedFlow()


    suspend fun loadContent() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        val result = repo.fetchJokes()

        if (result.isSuccess && result.getOrNull() != null) {
            _uiState.update {
                it.copy(isLoading = false, content = result.getOrNull()?: emptyList())
            }
        } else {
            _uiEvents.emit(UiEvents.SnackbarEvent("Failed to Load", true))
        }
    }
}

sealed class UiEvents {
    data class SnackbarEvent(val message: String, val errorSnackbar: Boolean): UiEvents()
}