package com.zeroninedev.core_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun YahhooTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = yahhooTypography,
        shapes = YahhooShapes,
        content = content
    )
}

private val LightColors = lightColors(
    primary = PrimaryDay,
    primaryVariant = TextDay,
    secondary = Teal200,
    background = BackgroundDay,
    onSurface = SurfaceDay,
    error = Red800
)

private val DarkColors = darkColors(
    primary = PrimaryNight,
    primaryVariant = TextNight,
    secondary = Teal200,
    background = BackgroundNight,
    onSurface = SurfaceNight,
    error = Red200
)
