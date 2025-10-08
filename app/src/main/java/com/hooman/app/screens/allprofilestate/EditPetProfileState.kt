package com.hooman.app.screens.allprofilestate



enum class PetType {
    DOG, CAT, BIRD, FISH, OTHER
}

enum class PetGender {
    MALE, FEMALE
}

data class EditPetProfileState(
    val profileImageUrl: String? = null,
    val petName: String = "",
    val selectedPetType: PetType = PetType.DOG,
    val breed: String = "",
    val age: String = "", // Example: "2 years (24 Human years)"
    val selectedGender: PetGender = PetGender.MALE,
    val weight: String = "",
    val weightUnit: String = "kg",
    val isNeutered: Boolean = true,
    val isLoading: Boolean = false
)