package com.zeroninedev.authentication.presentation.baseauth.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.authentication.domain.usecase.SendTokenUseCase
import com.zeroninedev.authentication.presentation.baseauth.screen.BaseAuthState
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthIntent.GoogleTokenCollect
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthIntent.SendErrorAction
import com.zeroninedev.authentication.presentation.baseauth.viewmodel.BaseAuthIntent.SetNavigator
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.navigation.actions.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BaseAuthViewModel @Inject constructor(
    private val sendTokenUseCase: SendTokenUseCase,
) : BaseViewModel<BaseAuthState>(BaseAuthState.Ready) {

    private var navigator: Navigator? = null

    fun processIntent(intent: BaseAuthIntent) = when (intent) {
        is GoogleTokenCollect -> sendGoogleToken(intent.token)
        is SendErrorAction -> sendError(intent.message)
        is SetNavigator -> setNavigator(intent.navigator)
    }

    private fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    private fun sendGoogleToken(token: String) {
        _screenState.value = BaseAuthState.LoadingGoogle
        viewModelScope.launch {
            runCatching { sendTokenUseCase(token) }
                .onSuccess { success() }
                .onFailure { sendError(it.message) }
        }
    }

    private fun success() {
        _screenState.value = BaseAuthState.SuccessGoogle
        navigator?.goBackStack()
    }

    private fun sendError(message: String? = null) {
        _screenState.value = BaseAuthState.Error(message ?: "Sheet happens")
    }
}