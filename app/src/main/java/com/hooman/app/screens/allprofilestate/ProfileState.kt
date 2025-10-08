package com.hooman.app.screens.allprofilestate

// Represents the data for a single pet
data class Pet(
    val name: String,
    val details: String, // e.g., "Labrador retriever • 3 years"
    val gender: String,
    val category: String // e.g., "Adult"
)

// Represents the data for the user
data class User(
    val name: String,
    val handle: String,
    val initials: String
)

// Holds the entire state for the Profile Screen
data class ProfileState(
    val user: User,
    val pet: Pet
)