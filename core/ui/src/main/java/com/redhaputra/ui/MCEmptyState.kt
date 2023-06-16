package com.redhaputra.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme

private const val DEFAULT_IMG_WIDTH = 100
private const val DEFAULT_IMG_HEIGHT = 100

/**
 * Movie compose empty state reusable component
 */
@Composable
fun MCEmptyState(
    modifier: Modifier = Modifier,
    imgPainter: Painter = painterResource(MCIcons.icEmptyList),
    imgWidth: Dp = DEFAULT_IMG_WIDTH.dp,
    imgHeight: Dp = DEFAULT_IMG_HEIGHT.dp,
    description: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(width = imgWidth, height = imgHeight),
            painter = imgPainter,
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            text = description,
            textAlign = TextAlign.Center,
            color = MCTheme.primaryColors.neutral700,
            style = MCTheme.typography.textLargeM
        )
    }
}