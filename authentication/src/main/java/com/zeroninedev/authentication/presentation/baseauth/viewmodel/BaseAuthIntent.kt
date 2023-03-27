package com.zeroninedev.authentication.presentation.baseauth.viewmodel

import com.zeroninedev.navigation.actions.Navigator

internal sealed class BaseAuthIntent {

    data class SendErrorAction(val message: String? = null) : BaseAuthIntent()

    data class GoogleTokenCollect(val token: String) : BaseAuthIntent()

    data class SetNavigator(val navigator: Navigator) : BaseAuthIntent()
}