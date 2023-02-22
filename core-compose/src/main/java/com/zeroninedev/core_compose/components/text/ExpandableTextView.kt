package com.zeroninedev.core_compose.components.text

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import com.zeroninedev.core_compose.R
import com.zeroninedev.core_compose.R.drawable
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

/**
 * Squashed text with icon which expand text
 *
 * @param modifier entered modifier from other scope
 * @param text text
 */
@ExperimentalAnimationApi
@Composable
fun ExpandableTextView(
    modifier: Modifier = Modifier,
    text: String
) {
    var expandState by remember { mutableStateOf(false) }

    val transition = updateTransition(expandState, label = String())

    val rotateValue by transition.animateFloat(
        transitionSpec = { tween(durationMillis = DURATION_ANIMATION) },
        label = String(),
    ) { screenState ->
        if (screenState == DOWN) START_ARROW_POSITION
        else END_ARROW_POSITION
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedContent(
            targetState = expandState,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = DURATION_ANIMATION)) with
                        fadeOut(animationSpec = tween(durationMillis = DURATION_ANIMATION)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState == DOWN)
                                keyframes {
                                    IntSize(initialSize.width, initialSize.height) at DURATION_ANIMATION
                                    durationMillis = DURATION_ANIMATION
                                }
                            else
                                keyframes {
                                    IntSize(targetSize.width, targetSize.height) at DURATION_ANIMATION
                                    durationMillis = DURATION_ANIMATION
                                }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded == DOWN) {
                Text(
                    text = text,
                    style = yahhooTypography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    maxLines = MAX_LINES_WHEN_SQUASHED,
                    modifier = modifier.padding(horizontal = MediumSize, vertical = TinySize)
                )
            } else {
                Text(
                    text = text,
                    style = yahhooTypography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = modifier.padding(horizontal = MediumSize, vertical = TinySize)
                )
            }
        }


        Icon(
            modifier = modifier
                .rotate(rotateValue)
                .clickable { expandState = !expandState },
            painter = painterResource(drawable.arrow_down_24),
            tint = MaterialTheme.colors.primaryVariant,
            contentDescription = stringResource(id = R.string.arrow_down_icon_content_description)
        )
    }
}

private const val START_ARROW_POSITION = 0F
private const val END_ARROW_POSITION = 180F
private const val MAX_LINES_WHEN_SQUASHED = 2
private const val DOWN = false
private const val DURATION_ANIMATION = 300