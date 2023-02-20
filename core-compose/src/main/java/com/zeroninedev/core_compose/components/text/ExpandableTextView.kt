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
import androidx.compose.ui.unit.IntSize
import com.zeroninedev.core_compose.R.drawable
import com.zeroninedev.core_compose.ui.theme.MediumSize
import com.zeroninedev.core_compose.ui.theme.TinySize
import com.zeroninedev.core_compose.ui.theme.yahhooTypography

private const val DOWN = false
private const val DURATION_ANIMATION = 300

@ExperimentalAnimationApi
@Composable
fun ExpandableTextView(
    modifier: Modifier = Modifier,
    text: String
) {
    var expandState by remember { mutableStateOf(false) }

    val transition = updateTransition(expandState, label = "")

    val rotateValue by transition.animateFloat(
        transitionSpec = { tween(durationMillis = DURATION_ANIMATION) },
        label = "",
    ) { screenState ->
        if (screenState == DOWN) {
            0f
        } else {
            180f
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedContent(
            targetState = expandState,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 150)) with
                        fadeOut(animationSpec = tween(durationMillis = 150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState == DOWN) {
                                keyframes {
                                    IntSize(initialSize.width, initialSize.height) at 150
                                    durationMillis = DURATION_ANIMATION
                                }
                            } else {
                                keyframes {
                                    IntSize(targetSize.width, targetSize.height) at 150
                                    durationMillis = DURATION_ANIMATION
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded == DOWN) {
                Text(
                    text = text,
                    style = yahhooTypography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    maxLines = 2,
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
            contentDescription = ""
        )
    }
}