package com.zeroninedev.core_compose.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.ui.theme.BigSize
import com.zeroninedev.core_compose.ui.theme.NormalMediumSize
import com.zeroninedev.core_compose.ui.theme.OneSize
import com.zeroninedev.core_compose.ui.theme.SmallSize
import com.zeroninedev.core_compose.ui.theme.TinySize

@Composable
fun SimpleButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(vertical = TinySize, horizontal = NormalMediumSize)
            .clip(MaterialTheme.shapes.medium), onClick = onButtonClick
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primaryVariant,
        )
    }
}

@Composable
fun OutlinedSimpleButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .padding(vertical = TinySize, horizontal = NormalMediumSize)
            .clip(MaterialTheme.shapes.medium),
        border = BorderStroke(
            OneSize,
            MaterialTheme.colors.primary
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        onClick = onButtonClick
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primaryVariant,
        )
    }
}

@Composable
fun GoogleSignButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier
            .padding(vertical = TinySize, horizontal = NormalMediumSize)
            .clip(MaterialTheme.shapes.medium),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.google),
            modifier = Modifier
                .size(BigSize),
            contentDescription = ""
        )
        Text(
            text = "Sign in with Google",
            modifier = Modifier.padding(horizontal = SmallSize, vertical = OneSize),
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun RoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
            .background(Color.Black)
            .clickable { onButtonClick() },
    ) {
        Text(
            modifier = Modifier.padding(horizontal = SmallSize, vertical = OneSize),
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            maxLines = 1
        )
    }
}