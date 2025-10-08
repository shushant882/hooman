package com.hooman.app.viewmodel

// Replace with your package name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// This data class holds the state for the UI
data class OnboardingUiState(
    val screen1TerminalText: String = "",
    val screen2UserMessageVisible: Boolean = false,
    val screen2AiMessageVisible: Boolean = false,
)

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState = _uiState.asStateFlow()

    fun startScreen1Animation() {
        viewModelScope.launch {
            // 1. Define all the lines of text you want to show in a list.
            val textLines = listOf(
                "> Analyzing pet behavior....",
                "> Health Score: 98%",
                "> Next checkup: 2 weeks"
            )

            // Reset the text to start the animation fresh.
            _uiState.update { it.copy(screen1TerminalText = "") }
            delay(200) // A small delay before starting.

            // 2. Loop through each line in the list.
            textLines.forEach { line ->
                // This inner loop types out the current line character by character.
                line.forEach { char ->
                    _uiState.update { currentState ->
                        currentState.copy(screen1TerminalText = currentState.screen1TerminalText + char)
                    }
                    delay(50) // Adjust the typing speed here (in milliseconds).
                }

                // 3. Add a newline character to move to the next line.
                _uiState.update { currentState ->
                    currentState.copy(screen1TerminalText = currentState.screen1TerminalText + "\n")
                }

                // 4. Wait for a moment before typing the next line.
                delay(400) // Adjust the pause between lines here.
            }
        }
    }
    // Logic for Screen 2's chat animation
    fun startScreen2Animation() {
        viewModelScope.launch {
            // Reset state in case we navigate back
            _uiState.update { it.copy(screen2UserMessageVisible = false, screen2AiMessageVisible = false) }

            delay(1500)
            _uiState.update { it.copy(screen2UserMessageVisible = true) }
            delay(2000)
            _uiState.update { it.copy(screen2AiMessageVisible = true) }
        }
    }
}