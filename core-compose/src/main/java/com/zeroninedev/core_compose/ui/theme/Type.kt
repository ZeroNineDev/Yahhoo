package com.zeroninedev.core_compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zeroninedev.core_compose.R

val Monserrat = FontFamily(
    Font(R.font.montserrat_black, weight = FontWeight.Black, style = FontStyle.Normal),
    Font(R.font.montserrat_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(R.font.montserrat_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.montserrat_extra_bold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(R.font.montserrat_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.montserrat_extra_light, weight = FontWeight.ExtraLight, style = FontStyle.Normal),
    Font(R.font.montserrat_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(R.font.montserrat_italic, style = FontStyle.Italic, weight = FontWeight.Normal),
    Font(R.font.montserrat_light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(R.font.montserrat_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.montserrat_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold, style = FontStyle.Normal),
    Font(R.font.montserrat_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.montserrat_thin, weight = FontWeight.Thin, style = FontStyle.Normal),
    Font(R.font.montserrat_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic)
)

val yahhooTypography = Typography(
    h2 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 48.sp
    ),
    h3 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 36.sp
    ),
    h4 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2 = TextStyle(
        fontFamily = Monserrat,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
