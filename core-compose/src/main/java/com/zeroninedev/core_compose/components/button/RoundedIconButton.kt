package com.zeroninedev.core_compose.components.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.zeroninedev.core_compose.ui.theme.BackgroundLightGray
import com.zeroninedev.core_compose.ui.theme.FiftySize
import com.zeroninedev.core_compose.ui.theme.SmallSize

/**
 * Rounded button with icon or image and text if it need
 *
 * @param icon icon drawable res
 * @param onClick callback on button click
 * @param modifier modifier
 * @param backgroundColor background button color
 * @param isIconRotate state rotate icon
 * @param rotateValue value of rotate icon
 * @param contentDescription content description
 * @param isIcon icon or image
 * @param tint tint for icon
 * @param text text for button
 * @param textMaxLines max lines in button for text
 */
@Composable
fun RoundedIconButton(
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = BackgroundLightGray,
    isIconRotate: Boolean = false,
    rotateValue: Float = 0f,
    contentDescription: String = String(),
    isIcon: Boolean = true,
    tint: Color = MaterialTheme.colors.primaryVariant,
    text: String? = null,
    textMaxLines: Int = 1
) {
    val rotationState by animateFloatAsState(targetValue = if (isIconRotate) rotateValue else 0f)

    Column(
        modifier = modifier
            .rotate(degrees = rotationState)
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(all = SmallSize)
                .size(size = FiftySize)
                .clip(shape = CircleShape)
                .background(color = backgroundColor)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            if (isIcon) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = icon),
                    contentDescription = contentDescription,
                    tint = tint
                )
            } else {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = icon),
                    contentDescription = contentDescription
                )
            }
        }

        if (!text.isNullOrEmpty()) {
            Text(
                modifier = Modifier,
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = textMaxLines
            )
        }
    }
}