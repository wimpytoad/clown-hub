package com.toadfrogson.clownhub.app.viewmodel

import androidx.lifecycle.ViewModel
import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.repo.JokesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainState(val isLoading: Boolean = false, val content: List<JokeModel> = emptyList())

class MainViewModel(private val repo: JokesRepo): ViewModel() {
    private val privateUiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = privateUiState.asStateFlow()

    suspend fun loadContent() {
        privateUiState.update {
            it.copy(isLoading = true)
        }
        val result = repo.fetchJokes()
        if (result.isSuccess && result.getOrNull() != null) {
            privateUiState.update {
                it.copy(isLoading = false, content = result.getOrNull()?: emptyList())
            }
        } else {

        }
    }
}