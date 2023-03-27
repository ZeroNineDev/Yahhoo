package com.zeroninedev.authentication.presentation.login.viewmodel

import com.zeroninedev.navigation.actions.Navigator

internal sealed class LoginIntent {

    data class SetNavigator(val navigator: Navigator) : LoginIntent()

    data class SignInUser(val email: String, val password: String) : LoginIntent()
}
