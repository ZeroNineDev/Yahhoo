package com.zeroninedev.authentication.presentation.register.viewmodel

import androidx.lifecycle.viewModelScope
import com.zeroninedev.authentication.domain.usecase.RegisterUserUseCase
import com.zeroninedev.authentication.presentation.register.screen.RegisterState
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterIntent.RegisterUser
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterIntent.SetNavigator
import com.zeroninedev.common.base.BaseViewModel
import com.zeroninedev.navigation.actions.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<RegisterState>(RegisterState.Ready) {

    private var navigator: Navigator? = null

    fun processIntent(intent: RegisterIntent) = when (intent) {
        is RegisterUser -> registerUser(intent.email, intent.password)
        is SetNavigator -> setNavigator(intent.navigator)
    }

    private fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            runCatching { registerUserUseCase(email, password) }
                .onSuccess { processSuccess() }
                .onFailure { _screenState.value = RegisterState.Error(it.message ?: "Sheet happens") }
        }
    }

    private fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    private fun processSuccess() {
        _screenState.value = RegisterState.Success
        navigator?.goBackStack()
    }
}