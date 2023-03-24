package com.zeroninedev.core_compose.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.components.Visibility
import com.zeroninedev.core_compose.components.VisibilityOff
import com.zeroninedev.core_compose.ui.theme.YahhooTheme

@ExperimentalComposeUiApi
@Composable
fun PasswordInputField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValue: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .wrapContentHeight(),
        value = value,
        onValueChange = { onValue(it) },
        label = { LabelView(title = label) },
        textStyle = typography.body1,
        colors = textFieldColors(),
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation =
        if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(topEnd = 4.dp, topStart = 4.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password_icon),
                contentDescription = null
            )
        },
        trailingIcon = {
            val image = if (passwordVisible.value) Filled.Visibility
            else Filled.VisibilityOff

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = image, contentDescription = null)
            }
        }
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun PasswordInputFieldPreview() {
    YahhooTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            AccountInputField(
                "Логин",
                "Логин"
            )
            {}
            PasswordInputField(
                "Пароль",
                "Пароль"
            )
            {}
        }
    }
}