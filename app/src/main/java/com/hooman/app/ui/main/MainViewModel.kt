package com.hooman.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    // Inject repositories or use cases here
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    fun loadMessage() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(1000) // simulate network/db call
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                message = "Hi Here! 👋"
            )
        }
    }

    fun incrementCounter() {
        _uiState.value = _uiState.value.copy(
            counter = _uiState.value.counter + 1
        )
    }
}
