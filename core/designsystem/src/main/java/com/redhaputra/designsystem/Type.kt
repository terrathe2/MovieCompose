package com.redhaputra.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * https://fonts.google.com/specimen/Poppins
 */
val PoppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

/**
 * Typography of Movie Compose
 *
 * Typography that will/might be used in Movie Compose App
 */
@Immutable
data class MCTypography(
    val headingBanner: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 33.sp,
        fontWeight = FontWeight.SemiBold
    ),
    val headingLargeSB: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 19.sp,
        fontWeight = FontWeight.SemiBold
    ),
    val headingLargeM: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium
    ),
    val headingMediumM: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium
    ),
    val textLargeR: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal
    ),
    val textLargeM: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium
    ),
    val textMediumR: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    val textMediumM: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    val textSmallR: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    ),
    val textSmallM: TextStyle = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium
    ),
)

val LocalCustomTypography = staticCompositionLocalOf {
    MCTypography()
}

/**
 * Primary Colors of Movie Compose
 *
 * Color Assets that will/might be used in Movie Compose App
 */
@Immutable
data class MCPrimaryColors(
    val dark: Color = Color.Unspecified,
    val primary700: Color = Color.Unspecified,
    val primary300: Color = Color.Unspecified,
    val neutral100: Color = Color.Unspecified,
    val neutral200: Color = Color.Unspecified,
    val neutral300: Color = Color.Unspecified,
    val neutral400: Color = Color.Unspecified,
    val neutral500: Color = Color.Unspecified,
    val neutral600: Color = Color.Unspecified,
    val neutral700: Color = Color.Unspecified
)

/**
 * Secondary Colors of Movie Compose
 *
 * Color Assets that will/might be used in Movie Compose App
 */
@Immutable
data class MCNeutralColors(
    val neutral100: Color = Color.Unspecified,
    val neutral200: Color = Color.Unspecified,
    val neutral300: Color = Color.Unspecified,
    val neutral400: Color = Color.Unspecified,
    val neutral500: Color = Color.Unspecified,
    val neutral600: Color = Color.Unspecified,
    val neutral700: Color = Color.Unspecified
)

/**
 * Accent Colors of Movie Compose
 *
 * Color Assets that will/might be used in Movie Compose App
 */
@Immutable
data class MCAccentColors(
    val red700: Color = Color.Unspecified,
    val orange500: Color = Color.Unspecified,
    val green700: Color = Color.Unspecified
)

val LocalCustomPrimaryColors = staticCompositionLocalOf { MCPrimaryColors() }
val LocalCustomNeutralColors = staticCompositionLocalOf { MCNeutralColors() }
val LocalCustomAccentColors = staticCompositionLocalOf { MCAccentColors() }