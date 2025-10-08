package com.hooman.app.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hooman.app.screens.allprofilestate.Pet
import com.hooman.app.screens.allprofilestate.ProfileState
import com.hooman.app.screens.allprofilestate.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    // Holds the UI state, observed by the Composable
    private val _uiState = MutableStateFlow(
        ProfileState(
            user = User(
                name = "Dishank Shekokare",
                handle = "@DISHANK.S",
                initials = "DS"
            ),
            pet = Pet(
                name = "Buddy",
                details = "Labrador retriever • 3 years",
                gender = "Male",
                category = "Adult"
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    // Used to send one-time navigation events to the UI
    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    /**
     * Handles user actions and posts navigation events.
     * The UI will observe these events and trigger the actual navigation.
     */
    fun onNavigate(route: String) {
        viewModelScope.launch {
            _navigationEvent.emit(route)
        }
    }

    /**
     * Handles the logout logic.
     */
    fun onLogout() {
        // TODO: Add your logout logic here (e.g., clear tokens, call API)
        onNavigate("login") // Example: navigate to login screen after logout
    }
}