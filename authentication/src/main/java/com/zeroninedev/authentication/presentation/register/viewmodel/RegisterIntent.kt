package com.zeroninedev.authentication.presentation.register.viewmodel

import com.zeroninedev.navigation.actions.Navigator

internal sealed class RegisterIntent {

    data class SetNavigator(val navigator: Navigator) : RegisterIntent()

    data class RegisterUser(val email: String, val password: String) : RegisterIntent()
}
