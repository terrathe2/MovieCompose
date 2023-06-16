package com.redhaputra.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.redhaputra.designsystem.theme.OceanBlue

private const val DEFAULT_ICON_TOUCHABLE_PADDING = 8

/**
 * Movie Compose custom clickable icon
 *
 * @param icon: icon to show (receive Painter only for now)
 * @param backgroundColor: background of the icon area
 * @param iconColor: tint color of the icon
 * @param iconContentDesc: content description of icon, nullable
 * @param iconTouchablePadding: touchable area size of the icon
 * @param clickAction: method to handle icon click action
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MCClickableIcon(
    icon: Painter,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    clickAction: () -> Unit = {},
    iconTouchablePadding: PaddingValues = PaddingValues(DEFAULT_ICON_TOUCHABLE_PADDING.dp),
    backgroundColor: Color = Color.White,
    enabled: Boolean = true,
    iconColor: Color = OceanBlue,
    iconContentDesc: String? = null,
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        IconButton(
            modifier = modifier
                .background(backgroundColor)
                .padding(iconTouchablePadding),
            enabled = enabled,
            onClick = clickAction
        ) {
            Icon(
                modifier = iconModifier,
                painter = icon,
                tint = iconColor,
                contentDescription = iconContentDesc
            )
        }
    }
}