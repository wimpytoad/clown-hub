package com.toadfrogson.clownhub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.model.response.ApiResponse
import com.toadfrogson.clownhub.data.repo.JokesRepo
import com.toadfrogson.clownhub.presentation.model.JokeType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainState(
    val isLoading: Boolean = false,
    val content: List<JokeModel> = emptyList(),
    val selectedFilter: JokeType = JokeType.AllJokes
)

class MainViewModel(private val repo: JokesRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: SharedFlow<UiEvents> = _uiEvents.asSharedFlow()

    private val unfilteredContent = mutableListOf<JokeModel>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadContent()
        }
    }

    suspend fun loadContent() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        val result = repo.fetchJokes()

        if (result is ApiResponse.Success) {
            unfilteredContent.addAll(result.data.orEmpty())
            _uiState.update {
                it.copy(isLoading = false, content = unfilteredContent)
            }
        } else {
            _uiState.update {
                it.copy(isLoading = false)
            }
            _uiEvents.emit(UiEvents.ErrorSnackbarEvent)
        }
    }

    fun filterContent(jokeType: JokeType) {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredContent = if (jokeType.value.isEmpty()) unfilteredContent
            else unfilteredContent.filter { it.category == jokeType.value }
            _uiState.update {
                it.copy(content = filteredContent, selectedFilter = jokeType)
            }
        }
    }

}

sealed class UiEvents {
    data object ErrorSnackbarEvent : UiEvents()
}