package com.hooman.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.hooman.app.ui.navigation.EmailSignUpNavEvent

@HiltViewModel
class EmailSignUpViewModel @Inject constructor(
    // private val authRepository: AuthRepository
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<EmailSignUpNavEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    // Example: Called after successful sign-up logic
    fun onSignUpSuccess() {
        viewModelScope.launch {
            // Add actual sign-up logic here (e.g., call repository)
            // If successful:
            _navigationEvent.emit(EmailSignUpNavEvent.SignUpSuccess)
        }
    }

    fun onNavigateBackClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(EmailSignUpNavEvent.NavigateBack)
        }
    }

    fun onTermsOfUseClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(EmailSignUpNavEvent.TermsOfUse)
        }
    }

    fun onPrivacyPolicyClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(EmailSignUpNavEvent.PrivacyPolicy)
        }
    }
}
