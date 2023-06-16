package com.redhaputra.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.redhaputra.designsystem.R
import com.redhaputra.designsystem.theme.MCTheme

/**
 * The Loading Component for [Surface] layout composable
 */
@Composable
fun MCLoadingSurface(
    loading: Boolean,
    loadingText: String? = stringResource(R.string.loading),
    loadingTextStyle: TextStyle = MCTheme.typography.textLargeM,
    overlayColor: Color = Color.White.copy(alpha = 0.54f),
    content: @Composable () -> Unit
) {
    content()

    if (loading) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = overlayColor
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()

                if (loadingText != null) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = loadingText,
                        color = MCTheme.primaryColors.neutral700,
                        style = loadingTextStyle
                    )
                }
            }
        }
    }
}