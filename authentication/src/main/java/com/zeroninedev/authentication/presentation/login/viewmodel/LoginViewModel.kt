package com.zeroninedev.authentication.presentation.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.authentication.domain.usecase.RegisterUserUseCase
import com.zeroninedev.authentication.presentation.login.screen.LoginState
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginIntent.SetNavigator
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginIntent.SignInUser
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.navigation.actions.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<LoginState>(LoginState.Ready) {

    private var navigator: Navigator? = null

    fun processIntent(intent: LoginIntent) = when (intent) {
        is SetNavigator -> setNavigator(intent.navigator)
        is SignInUser -> signInUser(intent.email, intent.password)
    }

    private fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            runCatching { registerUserUseCase(email, password) }
                .onSuccess { processSuccess() }
                .onFailure { _screenState.value = LoginState.Error(it.message ?: "Sheet happens") }
        }
    }

    private fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    private fun processSuccess() {
        _screenState.value = LoginState.Success
        navigator?.goBackStack()
    }
}