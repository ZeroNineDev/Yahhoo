package com.zeroninedev.core_compose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val YahhooShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(26.dp)
)

val RoundedCornerForBottomSheet = RoundedCornerShape(
    topStart = 30.dp,
    topEnd = 30.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)