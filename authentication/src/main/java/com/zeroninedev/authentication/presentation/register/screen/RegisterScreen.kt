package com.zeroninedev.authentication.presentation.register.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import com.zeroninedev.authentication.presentation.register.screen.RegisterState.Error
import com.zeroninedev.authentication.presentation.register.screen.RegisterState.Loading
import com.zeroninedev.authentication.presentation.register.screen.RegisterState.Ready
import com.zeroninedev.authentication.presentation.register.screen.RegisterState.Success
import com.zeroninedev.authentication.presentation.register.view.RegisterView
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterIntent.RegisterUser
import com.zeroninedev.authentication.presentation.register.viewmodel.RegisterViewModel
import com.zeroninedev.core_compose.R.string

@ExperimentalComposeUiApi
@Composable
internal fun RegisterScreen(
    viewModel: RegisterViewModel
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

        RegisterView { user, password ->
            viewModel.processIntent(RegisterUser(user, password))
        }
    }
}