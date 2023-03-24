package com.zeroninedev.core_compose.components.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.YahhooTheme

@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colors.primaryVariant,
    focusedLabelColor = MaterialTheme.colors.primary,
    focusedIndicatorColor = MaterialTheme.colors.primary,
    unfocusedIndicatorColor = Color.LightGray,
    cursorColor = MaterialTheme.colors.primary,
    placeholderColor = MaterialTheme.colors.onSurface,
    disabledPlaceholderColor = MaterialTheme.colors.onSurface
)

@Composable
fun LabelView(
    title: String
) {
    Text(
        text = title,
        style = typography.caption,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.primary
    )
}

@ExperimentalComposeUiApi
@Composable
fun AccountInputField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValue: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = MediumSize, end = MediumSize)
            .wrapContentHeight(),
        value = value,
        onValueChange = { onValue(it) },
        label = { LabelView(title = label) },
        textStyle = typography.body1,
        colors = textFieldColors(),
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        shape = RoundedCornerShape(topEnd = 4.dp, topStart = 4.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.account_icon),
                contentDescription = null
            )
        }
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun AccountInputFieldPreview() {
    YahhooTheme {
        AccountInputField(
            "Логин",
            "Логин"
        )
        {}
    }
}