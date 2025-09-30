package com.hooman.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.hooman.app.ui.navigation.EmailSignInNavEvent

@HiltViewModel
class EmailSignInViewModel @Inject constructor(
    // private val authRepository: AuthRepository
) : ViewModel() {
    private val _navigationEvent = MutableSharedFlow<EmailSignInNavEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    // Example: Called after successful sign-in logic
    fun onSignInSuccess() {
        viewModelScope.launch {
            // Add actual sign-in logic here (e.g., call repository)
            // If successful:
            _navigationEvent.emit(EmailSignInNavEvent.SignInSuccess)
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(EmailSignInNavEvent.NavigateToForgotPassword)
        }
    }

    fun onNavigateBackClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(EmailSignInNavEvent.NavigateBack)
        }
    }
}
