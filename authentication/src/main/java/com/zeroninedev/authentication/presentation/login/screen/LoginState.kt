package com.zeroninedev.authentication.presentation.login.screen

internal sealed class LoginState {

    object Ready : LoginState()

    object Loading : LoginState()

    object Success : LoginState()

    data class Error(val message: String) : LoginState()
}