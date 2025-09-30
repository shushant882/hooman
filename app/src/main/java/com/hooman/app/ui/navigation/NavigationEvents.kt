package com.hooman.app.ui.navigation

sealed interface LoginNavEvent {
    data object NavigateToEmailSignIn : LoginNavEvent
    data object NavigateToEmailSignUp : LoginNavEvent
    data object NavigateToAppleSignIn : LoginNavEvent
    data object NavigateToGoogleSignIn : LoginNavEvent
    data object NavigateToTermsOfService : LoginNavEvent
    data object NavigateToPrivacyPolicy : LoginNavEvent
}

sealed interface EmailSignInNavEvent {
    data object NavigateBack : EmailSignInNavEvent
    data object SignInSuccess : EmailSignInNavEvent
    // data object SignInFailure : EmailSignInNavEvent // future use
    data object NavigateToForgotPassword : EmailSignInNavEvent
}

sealed interface EmailSignUpNavEvent {
    data object NavigateBack : EmailSignUpNavEvent
    data object SignUpSuccess : EmailSignUpNavEvent
    // data object SignUpFailure : EmailSignUpNavEvent // future use
    data object TermsOfUse : EmailSignUpNavEvent
    data object PrivacyPolicy : EmailSignUpNavEvent
}
