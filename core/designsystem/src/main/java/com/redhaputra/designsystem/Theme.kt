package com.redhaputra.designsystem

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val primaryColors = MCPrimaryColors(
    dark = OceanBlue,
    primary700 = AzureRadiance,
    primary300 = IcyBlue,
    neutral100 = Color.White,
    neutral200 = Gray60,
    neutral300 = Gray88,
    neutral400 = Gray90,
    neutral500 = Gray,
    neutral600 = MCBlack98,
    neutral700 = MCBlack
)

private val neutralColors = MCNeutralColors(
    neutral100 = Color.White,
    neutral200 = Gray60,
    neutral300 = Gray88,
    neutral400 = Gray90,
    neutral500 = Gray,
    neutral600 = MCBlack98,
    neutral700 = MCBlack
)

private val accentColors = MCAccentColors(
    red700 = BrightRed,
    orange500 = YellowOrange,
    green700 = BrightGreen
)

private val LightColorPalette = lightColors(
    primary = OceanBlue
)

/**
 * Movie Compose theme
 *
 * Theme that used by the App
 */
@Composable
fun MCTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalCustomPrimaryColors provides primaryColors,
        LocalCustomNeutralColors provides neutralColors,
        LocalCustomAccentColors provides accentColors,
        LocalCustomTypography provides MCTypography(),
    ) {
        MaterialTheme(
            colors = LightColorPalette,
            content = content
        )
    }
}

/**
 * Return the value provided by the nearest CompositionLocalProvider component that invokes,
 * directly or indirectly, the composable function that uses this property.
 */
object MCTheme {
    val typography
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomTypography.current

    val primaryColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomPrimaryColors.current

    val neutralColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomNeutralColors.current

    val accentColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomAccentColors.current
}