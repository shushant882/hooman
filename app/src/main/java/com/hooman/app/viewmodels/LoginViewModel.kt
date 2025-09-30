package com.hooman.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.hooman.app.ui.navigation.LoginNavEvent

@HiltViewModel
class LoginViewModel @Inject constructor(
    // private val authRepository: AuthRepository // Future dependency
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<LoginNavEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEmailSignInClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToEmailSignIn)
        }
    }

    fun onEmailSignUpClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToEmailSignUp)
        }
    }

    fun onAppleSignInClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToAppleSignIn)
        }
    }

    fun onGoogleSignInClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToGoogleSignIn)
        }
    }

    fun onTermsOfServiceClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToTermsOfService)
        }
    }

    fun onPrivacyPolicyClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(LoginNavEvent.NavigateToPrivacyPolicy)
        }
    }
}
