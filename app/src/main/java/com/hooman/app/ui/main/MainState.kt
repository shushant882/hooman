package com.hooman.app.ui.main

// Immutable UI state for Compose
data class MainState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val counter: Int = 0
)
