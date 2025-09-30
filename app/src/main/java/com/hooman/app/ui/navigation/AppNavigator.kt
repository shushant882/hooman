package com.hooman.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hooman.app.screens.LoginScreen
import com.hooman.app.screens.EmailSignInScreen
import com.hooman.app.screens.EmailSignUpScreen
import com.hooman.app.viewmodels.LoginViewModel
import com.hooman.app.viewmodels.EmailSignInViewModel
import com.hooman.app.viewmodels.EmailSignUpViewModel

@Composable
fun AppNavigator(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(AppDestinations.LOGIN_ROUTE) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(viewModel = loginViewModel)

            LaunchedEffect(key1 = loginViewModel) {
                loginViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is LoginNavEvent.NavigateToEmailSignIn -> {
                            navController.navigate(AppDestinations.EMAIL_SIGN_IN_ROUTE)
                        }
                        is LoginNavEvent.NavigateToEmailSignUp -> {
                            navController.navigate(AppDestinations.EMAIL_SIGN_UP_ROUTE)
                        }

                        LoginNavEvent.NavigateToAppleSignIn -> TODO()
                        LoginNavEvent.NavigateToGoogleSignIn -> TODO()
                        LoginNavEvent.NavigateToPrivacyPolicy -> TODO()
                        LoginNavEvent.NavigateToTermsOfService -> TODO()
                    }
                }
            }
        }

        composable(AppDestinations.EMAIL_SIGN_IN_ROUTE) {
            val emailSignInViewModel: EmailSignInViewModel = hiltViewModel()
            EmailSignInScreen(viewModel = emailSignInViewModel)

            LaunchedEffect(key1 = emailSignInViewModel) {
                emailSignInViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is EmailSignInNavEvent.NavigateBack -> {
                            navController.popBackStack()
                        }
                        is EmailSignInNavEvent.SignInSuccess -> {
                            // TODO: Remove this line after implementing main app dashboard
                            navController.popBackStack(
                                route = AppDestinations.LOGIN_ROUTE,
                                inclusive = false // Keep LoginScreen on the stack
                            )
                            // TODO: Uncomment this to Navigate to main app dashboard
                            // navController.navigate(AppDestinations.MAIN_APP_DASHBOARD_ROUTE) {
                            //    popUpTo(AppDestinations.LOGIN_ROUTE) { inclusive = true }
                            // }
                        }

                        EmailSignInNavEvent.NavigateToForgotPassword -> TODO()
                    }
                }
            }
        }

        composable(AppDestinations.EMAIL_SIGN_UP_ROUTE) {
            val emailSignUpViewModel: EmailSignUpViewModel = hiltViewModel()
            EmailSignUpScreen(viewModel = emailSignUpViewModel)

            LaunchedEffect(key1 = emailSignUpViewModel) {
                emailSignUpViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is EmailSignUpNavEvent.NavigateBack -> {
                            navController.popBackStack()
                        }
                        is EmailSignUpNavEvent.SignUpSuccess -> {
                            // TODO: Decide what to do after successful sign-up
                            // For now: Navigate to Login screen after successful sign-up
                            navController.navigate(AppDestinations.LOGIN_ROUTE) {
                                popUpTo(AppDestinations.LOGIN_ROUTE) { inclusive = true }
                            }
                        }

                        EmailSignUpNavEvent.PrivacyPolicy -> TODO()
                        EmailSignUpNavEvent.TermsOfUse -> TODO()
                    }
                }
            }
        }

        // composable(AppDestinations.MAIN_APP_DASHBOARD_ROUTE) { /* ... Main App Screen ... */ }
    }
}
