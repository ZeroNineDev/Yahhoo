package com.zeroninedev.authentication.presentation.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.button.SimpleButton
import com.zeroninedev.core_compose.components.input.AccountInputField
import com.zeroninedev.core_compose.components.input.PasswordInputField

@ExperimentalComposeUiApi
@Composable
internal fun RegisterView(
    onRegister: (String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        AccountInputField(label = stringResource(id = com.zeroninedev.core_compose.R.string.email_text), value = email){
            email = it
        }
        PasswordInputField(label = stringResource(id = com.zeroninedev.core_compose.R.string.password_text), value = password) {
            password = it
        }

        SimpleButton(
            text = stringResource(id = com.zeroninedev.core_compose.R.string.register_text),
            modifier = Modifier.fillMaxWidth()
        ) {
            onRegister(email, password)
        }
    }

}