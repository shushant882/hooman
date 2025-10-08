package com.hooman.app.screens.allprofilestate

// Adjust package as needed

data class EditProfileState(
    val profileImageUrl: String? = null,
    val username: String = "",
    val fullName: String = "",
    val email: String = "",
    val dateOfBirth: String = "", // Could be a Date object for better handling
    val countryCode: String = "+234", // Example: Nigeria's code
    val phoneNumber: String = "",
    val selectedGender: Gender = Gender.MALE,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSaved: Boolean = false
)

enum class Gender {
    MALE, FEMALE
}