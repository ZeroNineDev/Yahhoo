package com.zeroninedev.authentication.navigation

import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zeroninedev.authentication.presentation.login.screen.LoginScreen
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginIntent
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginViewModel
import com.zeroninedev.authentication.presentation.register.screen.RegisterScreen
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterIntent
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterViewModel
import com.zeroninedev.navigation.actions.Navigator
import com.zeroninedev.navigation.destination.AuthenticationScreens

@ExperimentalComposeUiApi
@ExperimentalComposeApi
fun NavGraphBuilder.mainAuthenticationScreenNavigation(navigator: Navigator) {

    composable(AuthenticationScreens.RegisterScreen.ROUTE) {
        val registerViewModel: RegisterViewModel = hiltViewModel()
        registerViewModel.processIntent(RegisterIntent.SetNavigator(navigator))
        RegisterScreen(registerViewModel)
    }

    composable(AuthenticationScreens.LoginScreen.ROUTE) {
        val loginViewModel: LoginViewModel = hiltViewModel()
        loginViewModel.processIntent(LoginIntent.SetNavigator(navigator))
        LoginScreen(loginViewModel)
    }
}