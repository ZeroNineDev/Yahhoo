package com.zeroninedev.authentication.presentation.baseauth.screen

internal sealed class BaseAuthState {

    object Ready : BaseAuthState()

    object LoadingGoogle : BaseAuthState()

    object SuccessGoogle : BaseAuthState()

    data class Error(val message: String) : BaseAuthState()
}