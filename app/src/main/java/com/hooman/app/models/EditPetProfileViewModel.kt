package com.hooman.app.models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hooman.app.screens.allprofilestate.EditPetProfileState
import com.hooman.app.screens.allprofilestate.PetGender
import com.hooman.app.screens.allprofilestate.PetType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditPetProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditPetProfileState(
            petName = "Buddy",
            selectedPetType = PetType.DOG,
            breed = "Golden Retriever",
            age = "2 years (24 Human years)",
            selectedGender = PetGender.MALE,
            weight = "45",
            weightUnit = "kg",
            isNeutered = true
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<PetProfileNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onPetNameChange(name: String) {
        _uiState.value = _uiState.value.copy(petName = name)
    }

    fun onPetTypeSelected(type: PetType) {
        _uiState.value = _uiState.value.copy(selectedPetType = type)
    }

    fun onBreedChange(breed: String) {
        _uiState.value = _uiState.value.copy(breed = breed)
    }

    fun onAgeChange(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun onGenderSelected(gender: PetGender) {
        _uiState.value = _uiState.value.copy(selectedGender = gender)
    }

    fun onWeightChange(weight: String) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun onWeightUnitChange(unit: String) {
        _uiState.value = _uiState.value.copy(weightUnit = unit)
    }

    fun onNeuteredStatusChange(status: Boolean) {
        _uiState.value = _uiState.value.copy(isNeutered = status)
    }

    fun onDismiss() {
        viewModelScope.launch {
            _navigationEvent.emit(PetProfileNavigationEvent.NavigateBack)
        }
    }

    fun onSaveDetails() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(1500) // Simulate saving data
            _uiState.value = _uiState.value.copy(isLoading = false)
            _navigationEvent.emit(PetProfileNavigationEvent.NavigateBack)
        }
    }

    fun onDeleteProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(1500) // Simulate deleting data
            _uiState.value = _uiState.value.copy(isLoading = false)
            _navigationEvent.emit(PetProfileNavigationEvent.NavigateBack) // Or navigate to a different screen
        }
    }

    fun onChangePhoto() {
        viewModelScope.launch {
            _navigationEvent.emit(PetProfileNavigationEvent.PickImage)
        }
    }
}

sealed class PetProfileNavigationEvent {
    object NavigateBack : PetProfileNavigationEvent()
    object PickImage : PetProfileNavigationEvent()
}