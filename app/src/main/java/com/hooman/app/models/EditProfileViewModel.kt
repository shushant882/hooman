package com.hooman.app.models

import com.hooman.app.screens.allprofilestate.EditProfileState
import com.hooman.app.screens.allprofilestate.Gender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditProfileState(
            profileImageUrl = null, // Placeholder, load from actual user data
            username = "BillWill",
            fullName = "Bill Willam",
            email = "bill@will.com",
            dateOfBirth = "24 years",
            phoneNumber = "8023456789",
            selectedGender = Gender.MALE
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<EditProfileNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onUsernameChange(newUsername: String) {
        _uiState.value = _uiState.value.copy(username = newUsername)
    }

    fun onFullNameChange(newFullName: String) {
        _uiState.value = _uiState.value.copy(fullName = newFullName)
    }

    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    fun onDateOfBirthChange(newDob: String) {
        _uiState.value = _uiState.value.copy(dateOfBirth = newDob)
    }

    fun onPhoneNumberChange(newPhoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = newPhoneNumber)
    }

    fun onCountryCodeChange(newCode: String) {
        _uiState.value = _uiState.value.copy(countryCode = newCode)
    }

    fun onGenderSelected(gender: Gender) {
        _uiState.value = _uiState.value.copy(selectedGender = gender)
    }

    fun onSaveDetails() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            delay(1500) // Simulate network request
            // TODO: Implement actual save logic (API call, database update)

            // For demonstration, assume save is successful
            _uiState.value = _uiState.value.copy(isLoading = false, isSaved = true)
            _navigationEvent.emit(EditProfileNavigationEvent.NavigateBack)
        }
    }

    fun onDeleteProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            delay(2000) // Simulate network request
            // TODO: Implement actual delete logic (API call, clear local data)

            // For demonstration, assume delete is successful
            _uiState.value = _uiState.value.copy(isLoading = false)
            _navigationEvent.emit(EditProfileNavigationEvent.NavigateToLogin) // Or some other main screen
        }
    }

    fun onDismiss() {
        viewModelScope.launch {
            _navigationEvent.emit(EditProfileNavigationEvent.NavigateBack)
        }
    }

    // Function to handle changing profile photo (triggers image picker, etc.)
    fun onChangePhoto() {
        viewModelScope.launch {
            _navigationEvent.emit(EditProfileNavigationEvent.PickImage)
        }
    }
}

sealed class EditProfileNavigationEvent {
    object NavigateBack : EditProfileNavigationEvent()
    object NavigateToLogin : EditProfileNavigationEvent()
    object PickImage : EditProfileNavigationEvent()
}