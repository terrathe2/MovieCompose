package com.redhaputra.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.redhaputra.designsystem.R
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme

/**
 * Movie compose image with loading & empty state view reusable component
 */
@Composable
fun MCImageFromUrl(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    loadingText: String? = stringResource(id = R.string.downloading),
    loadingTextStyle: TextStyle = MCTheme.typography.textLargeM,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    contentDesc: String? = null
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = contentDesc,
        contentScale = contentScale,
        alignment = alignment
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading ->
                MCLoadingSurface(
                    loading = true,
                    loadingText = loadingText,
                    loadingTextStyle = loadingTextStyle
                ) {
                    SubcomposeAsyncImageContent(
                        painter = painterResource(id = MCIcons.icEmptyImg),
                        contentScale = ContentScale.None
                    )
                }
            is AsyncImagePainter.State.Error, AsyncImagePainter.State.Empty ->
                SubcomposeAsyncImageContent(
                    painter = painterResource(id = MCIcons.icEmptyImg),
                    contentScale = ContentScale.None
                )
            else -> SubcomposeAsyncImageContent()
        }
    }
}