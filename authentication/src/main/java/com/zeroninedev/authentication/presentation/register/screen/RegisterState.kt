package com.zeroninedev.authentication.presentation.register.screen

internal sealed class RegisterState {

    object Ready : RegisterState()

    object Loading : RegisterState()

    object Success : RegisterState()

    data class Error(val message: String) : RegisterState()
}