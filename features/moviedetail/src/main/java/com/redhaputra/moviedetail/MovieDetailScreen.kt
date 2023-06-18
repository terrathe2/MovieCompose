package com.redhaputra.moviedetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.redhaputra.designsystem.component.MCDefaultTopBarContent
import com.redhaputra.designsystem.theme.MCTheme
import com.redhaputra.model.data.MovieData
import com.redhaputra.moviedetail.state.MovieDetailUIState
import com.redhaputra.moviedetail.state.MovieTrailerListState
import com.redhaputra.navigation.constants.MovieDetailConstant.MOVIE_DATA_KEY
import com.redhaputra.ui.MCPullToRefresh
import com.redhaputra.ui.MCRectangleShimmer
import com.redhaputra.designsystem.R as RD

/**
 * Composable method to handle MovieDetailRoute
 */
@Composable
fun MovieDetailRoute(
    navController: NavHostController,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
) {
    val movieDetailUIState by viewModel.movieDetailUIState.collectAsStateWithLifecycle()
    val movieData by viewModel.movieData.collectAsStateWithLifecycle()

    HandleData(
        navController = navController,
        setMovieData = viewModel::setMovieData,
        getMovieTrailers = viewModel::getMovieTrailers
    )
    HandleErrorState(
        errorMsg = movieDetailUIState.errorState,
        resetErrorMsg = viewModel::resetErrorMessage,
        showSnackBar = showSnackBar
    )
    MovieDetailScreen(
        movieDetailUIState = movieDetailUIState,
        movieData = movieData,
        onBackClick = onBackClick
    )
}

@Composable
private fun HandleData(
    navController: NavHostController,
    setMovieData: (MovieData) -> Unit,
    getMovieTrailers: (Int) -> Unit,
) {
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val movieData = savedStateHandle?.get<MovieData>(MOVIE_DATA_KEY)

    LaunchedEffect(movieData) {
        movieData?.let {
            setMovieData(it)
            getMovieTrailers(it.id)
            // removing movie data after set, so the data will not persist in saveState
            savedStateHandle.remove<MovieData>(MOVIE_DATA_KEY)
        }
    }
}

@Composable
private fun HandleErrorState(
    errorMsg: String?,
    resetErrorMsg: () -> Unit,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
) {
    LaunchedEffect(errorMsg) {
        errorMsg?.let {
            resetErrorMsg.invoke()
            showSnackBar(it, null, SnackbarDuration.Short, null)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MovieDetailScreen(
    movieDetailUIState: MovieDetailUIState,
    movieData: MovieData?,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MCDefaultTopBarContent(
                title = stringResource(id = RD.string.movie_detail),
                onBackClick = onBackClick
            )
        },
    ) { padding ->
        MCPullToRefresh(
            modifier = Modifier.padding(padding),
            onRefresh = {}
        ) {
            MovieDetailContent(
                movieDetailUIState = movieDetailUIState,
                movieData = movieData
            )
        }
    }
}

@Composable
private fun MovieDetailContent(
    movieDetailUIState: MovieDetailUIState,
    movieData: MovieData?
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        movieDetailHeader(
            movieTrailerListState = movieDetailUIState.trailerState,
            movieData = movieData
        )
//        monitoringHistoryList(
//            monitoringHistoryListData = vccHistoryListData,
//            initialLoad = viewModel.initialLoad,
//            item = { data ->
//                VccSnapshotItem(
//                    modifier = Modifier
//                        .clickable { navigateToSnapshotDetail(data.engineAlias, data.id) }
//                        .padding(horizontal = 4.dp),
//                    category = data.category,
//                    createdAt = data.createdAt,
//                    imageUrl = data.imageUrl,
//                    iconUrl = data.iconUrl
//                )
//            }
//        )
    }
}

private fun LazyListScope.movieDetailHeader(
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
                YoutubePlayer(videoId = videoId)
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
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                text = "${stringResource(id = RD.string.rating)}: ${movieData?.vote ?: "-"}/10",
                style = MCTheme.typography.textSmallM.copy(
                    color = MCTheme.primaryColors.neutral500
                ),
            )
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
private fun YoutubePlayer(
    videoId: String,
) {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
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