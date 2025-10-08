package com.hooman.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hooman.app.screens.profilescreensall.EditPetProfileScreen
import com.hooman.app.screens.profilescreensall.ProfileScreen
import com.hooman.app.screens.profilescreensall.EditProfileScreen // Import the new screen

/**
 * A centralized object to hold all navigation route constants.
 * This prevents typos and makes route management easy.
 */
object AppRoutes {
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile" // This is the route for the new screen
    const val MANAGE_PETS = "manage_pets"
    const val EDIT_PET = "edit_pet"
    const val HEALTH_CARE = "health_care"
    const val LIFESTYLE_PREFERENCES = "lifestyle_preferences"
    const val NOTIFICATIONS = "notifications"
    const val UNITS = "units"
    const val LANGUAGE = "language"
    const val MY_PLAN = "my_plan"
    const val BILLING = "billing"
    const val FAQ = "faq"
    const val CONTACT_SUPPORT = "contact_support"
    const val TERMS_OF_SERVICE = "terms_of_service"
    const val PRIVACY_POLICY = "privacy_policy"
    const val LOGIN = "login"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.PROFILE
    ) {
        composable(route = AppRoutes.PROFILE) {
            ProfileScreen(
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        // --- Edit Profile Screen ---
        composable(route = AppRoutes.EDIT_PROFILE) {
            EditProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.PROFILE) { inclusive = true }
                    }
                },
                onPickImage = { /* TODO: Implement actual image picking logic */ }
            )
        }

        // --- Edit Pet Profile Screen ---
        composable(route = AppRoutes.EDIT_PET) {
            EditPetProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onPickImage = { /* TODO: Implement image picker logic */ }
            )
        }

        // --- Other destinations (using placeholders for now) ---
        composable(route = AppRoutes.MANAGE_PETS) { PlaceholderScreen("Manage Pets") }
        // composable(route = AppRoutes.EDIT_PET) { PlaceholderScreen("Edit Pet") } // <<< DELETE THIS LINE
        composable(route = AppRoutes.HEALTH_CARE) { PlaceholderScreen("Health & Care") }
        composable(route = AppRoutes.LIFESTYLE_PREFERENCES) { PlaceholderScreen("Lifestyle & Preferences") }
        composable(route = AppRoutes.NOTIFICATIONS) { PlaceholderScreen("Notifications") }
        composable(route = AppRoutes.UNITS) { PlaceholderScreen("Units") }
        composable(route = AppRoutes.LANGUAGE) { PlaceholderScreen("Language") }
        composable(route = AppRoutes.MY_PLAN) { PlaceholderScreen("My Plan") }
        composable(route = AppRoutes.BILLING) { PlaceholderScreen("Billing & Payments") }
        composable(route = AppRoutes.FAQ) { PlaceholderScreen("FAQ") }
        composable(route = AppRoutes.CONTACT_SUPPORT) { PlaceholderScreen("Contact Support") }
        composable(route = AppRoutes.TERMS_OF_SERVICE) { PlaceholderScreen("Terms of Service") }
        composable(route = AppRoutes.PRIVACY_POLICY) { PlaceholderScreen("Privacy Policy") }
        composable(route = AppRoutes.LOGIN) { PlaceholderScreen("Login Screen") }
    }
}

@Composable
fun PlaceholderScreen(screenName: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "You are on the $screenName screen.", color = Color.White)
    }
}