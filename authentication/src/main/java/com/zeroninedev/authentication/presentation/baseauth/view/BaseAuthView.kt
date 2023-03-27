package com.zeroninedev.authentication.presentation.baseauth.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zeroninedev.core_compose.components.button.GoogleSignButton
import com.zeroninedev.core_compose.components.button.OutlinedSimpleButton
import com.zeroninedev.core_compose.components.button.SimpleButton
import com.zeroninedev.core_compose.ui.theme.BigSize

@Composable
fun BaseAuthView(
    onGoogleSignClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
                    .asPaddingValues()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1F))
        SimpleButton(
            stringResource(id = com.zeroninedev.core_compose.R.string.sign_in_text),
            modifier = Modifier.fillMaxWidth(),
            onButtonClick = onLoginClick
        )
        OutlinedSimpleButton(
            stringResource(id = com.zeroninedev.core_compose.R.string.register_text),
            modifier = Modifier.fillMaxWidth(),
            onButtonClick = onRegisterClick
        )
        GoogleSignButton(
            modifier = Modifier.fillMaxWidth(),
            onButtonClick = onGoogleSignClick
        )
        Spacer(
            modifier = Modifier
                .height(BigSize)
        )
    }
}