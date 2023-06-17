package com.redhaputra.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.redhaputra.ui.utils.IntUtils.COMMON_RADIUS

private const val ANIMATION_DURATION = 1000
private const val ANIMATION_INITIAL_VALUE = 0f
private const val ANIMATION_TARGET_VALUE = 1000f
private const val DEFAULT_WIDTH = 40
private const val DEFAULT_HEIGHT = 20
private val defaultColorList = listOf(
    Color.LightGray.copy(alpha = 0.6f),
    Color.LightGray.copy(alpha = 0.2f),
    Color.LightGray.copy(alpha = 0.6f),
)

/**
 * Movie compose customable brush used for shimmer animated color
 *
 * @return Brush: put it in background color of Composable method
 */
@Composable
fun shimmerBrush(
    colors: List<Color> = defaultColorList,
    animInitialValue: Float = ANIMATION_INITIAL_VALUE,
    animTargetValue: Float = ANIMATION_TARGET_VALUE,
    animDuration: Int = ANIMATION_DURATION
): Brush {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = animInitialValue,
        targetValue = animTargetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animDuration,
                easing = FastOutSlowInEasing
            )
        )
    )
    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
}

/**
 * Movie compose rectangle shimmer reusable component
 */
@Composable
fun MCRectangleShimmer(
    modifier: Modifier = Modifier,
    width: Dp = DEFAULT_WIDTH.dp,
    height: Dp = DEFAULT_HEIGHT.dp
) {
    Spacer(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(COMMON_RADIUS.dp))
            .background(brush = shimmerBrush())
    )
}