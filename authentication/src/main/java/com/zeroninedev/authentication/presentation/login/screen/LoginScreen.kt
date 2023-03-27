package com.zeroninedev.authentication.presentation.login.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import com.zeroninedev.authentication.presentation.login.screen.LoginState.Error
import com.zeroninedev.authentication.presentation.login.screen.LoginState.Loading
import com.zeroninedev.authentication.presentation.login.screen.LoginState.Ready
import com.zeroninedev.authentication.presentation.login.screen.LoginState.Success
import com.zeroninedev.authentication.presentation.login.view.LoginView
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginIntent.SignInUser
import com.zeroninedev.authentication.presentation.login.viewmodel.LoginViewModel
import com.zeroninedev.core_compose.R.string

@ExperimentalComposeUiApi
@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel
) {
    Box(contentAlignment = Alignment.Center) {

        when (val result = viewModel.screenState.collectAsState().value) {
            is Error -> { Toast.makeText(LocalContext.current, result.message, Toast.LENGTH_SHORT).show() }
            Loading -> { CircularProgressIndicator() }
            Ready -> {  }
            is Success -> {
                Toast.makeText(
                    LocalContext.current,
                    string.success_text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        LoginView { user, password ->
            viewModel.processIntent(SignInUser(user, password))
        }
    }
}