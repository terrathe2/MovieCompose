package com.redhaputra.moviedetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.redhaputra.designsystem.R as RD
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme
import com.redhaputra.model.data.MovieData
import com.redhaputra.moviedetail.state.MovieTrailerListState
import com.redhaputra.ui.MCRectangleShimmer

/**
 * Header component of Movie Detail
 */
fun LazyListScope.movieDetailHeader(
    movieTrailerListState: MovieTrailerListState?,
    movieData: MovieData?
) {
    item {
        Column {
            if (movieTrailerListState is MovieTrailerListState.Loading) {
                MCRectangleShimmer(
                    modifier = Modifier.fillMaxWidth(),
                    height = 150.dp
                )
            } else {
                val videoId = if (movieTrailerListState is MovieTrailerListState.Success) {
                    movieTrailerListState.trailer?.key ?: ""
                } else ""

                if (videoId.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(MCTheme.primaryColors.neutral500),
                        painter = painterResource(id = RD.drawable.empty_image),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = null
                    )
                } else {
                    YoutubePlayer(videoId = videoId)
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp),
                text = movieData?.title ?: "-",
                style = MCTheme.typography.headingLargeM.copy(
                    color = MCTheme.primaryColors.neutral700
                ),
            )
            Rating(vote = movieData?.vote)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp),
                text = movieData?.overview ?: "-",
                style = MCTheme.typography.textMediumR.copy(
                    color = MCTheme.primaryColors.neutral700
                ),
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp),
                text = pluralStringResource(
                    id = RD.plurals.review,
                    count = 0 // changed later
                ),
                style = MCTheme.typography.textLargeM.copy(
                    color = MCTheme.primaryColors.neutral700
                ),
            )
        }
    }
}

@Composable
private fun Rating(vote: Double?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .size(18.dp)
                .padding(end = 4.dp),
            painter = painterResource(id = MCIcons.icRating),
            tint = MCTheme.accentColors.orange500,
            contentDescription = null
        )
        Text(
            text = "${vote ?: "-"}/10",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MCTheme.primaryColors.neutral500,
            style = MCTheme.typography.textSmallM.merge(
                TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            ),
        )
    }
}

@Composable
private fun YoutubePlayer(
    videoId: String,
) {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MCTheme.primaryColors.neutral500),
        factory = {
            val view = YouTubePlayerView(context)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )
            view
        }
    )
}