package com.redhaputra.moviedetail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.redhaputra.designsystem.component.MCDefaultTopBarContent
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme
import com.redhaputra.model.data.MovieData
import com.redhaputra.model.response.ItemMovieReviewsResponse
import com.redhaputra.moviedetail.component.movieDetailHeader
import com.redhaputra.moviedetail.state.MovieDetailUIState
import com.redhaputra.moviedetail.state.MovieTrailerListState
import com.redhaputra.navigation.constants.MovieDetailConstant.MOVIE_DATA_KEY
import com.redhaputra.ui.MCEmptyUIState
import com.redhaputra.ui.MCLoadMoreUIState
import com.redhaputra.ui.MCPagingErrorHandler
import com.redhaputra.ui.MCPullToRefresh
import com.redhaputra.ui.MCRectangleShimmer
import com.redhaputra.ui.shimmerBrush
import com.redhaputra.ui.utils.IntUtils
import kotlinx.coroutines.launch
import com.redhaputra.designsystem.R as RD

private val REVIEW_RATING_ICON_SIZE = 24.dp

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
    val movieReviews = viewModel.movieReviewsData.collectAsLazyPagingItems()

    HandleData(
        navController = navController,
        setMovieData = viewModel::setMovieData,
        getMovieTrailers = viewModel::getMovieTrailers,
        getMovieReviews = viewModel::getMovieReviews
    )
    HandleErrorState(
        errorMsg = movieDetailUIState.errorState,
        resetErrorMsg = viewModel::resetErrorMessage,
        showSnackBar = showSnackBar
    )
    MCPagingErrorHandler(loadState = movieReviews.loadState, showSnackBar = showSnackBar)
    MovieDetailScreen(
        movieDetailUIState = movieDetailUIState,
        movieData = movieData,
        movieReviews = movieReviews,
        onRefresh = viewModel::onRefresh,
        onBackClick = onBackClick
    )
}

@Composable
private fun HandleData(
    navController: NavHostController,
    setMovieData: (MovieData) -> Unit,
    getMovieTrailers: (Int) -> Unit,
    getMovieReviews: (Int) -> Unit,
) {
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val movieData = savedStateHandle?.get<MovieData>(MOVIE_DATA_KEY)

    LaunchedEffect(movieData) {
        movieData?.let {
            setMovieData(it)
            getMovieTrailers(it.id)
            getMovieReviews(it.id)
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
    movieReviews: LazyPagingItems<ItemMovieReviewsResponse>,
    onRefresh: () -> Unit,
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
            onRefresh = {
                if (
                    movieDetailUIState.trailerState !is MovieTrailerListState.Loading &&
                    movieReviews.loadState.refresh !is LoadState.Loading
                ) {
                    onRefresh()
                    movieReviews.refresh()
                }

            }
        ) {
            MovieDetailContent(
                movieDetailUIState = movieDetailUIState,
                movieData = movieData,
                pagingData = movieReviews
            )
        }
    }
}

@Composable
private fun MovieDetailContent(
    movieDetailUIState: MovieDetailUIState,
    movieData: MovieData?,
    pagingData: LazyPagingItems<ItemMovieReviewsResponse>,
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        movieDetailHeader(
            movieTrailerListState = movieDetailUIState.trailerState,
            movieData = movieData
        )
        movieReviewList(
            pagingData = pagingData,
        )
    }

    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ScrollToTopButton(onClick = {
            scope.launch {
                listState.animateScrollToItem(0)
            }
        })
    }
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp, end = 20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(40.dp),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MCTheme.primaryColors.primary700,
                contentColor = MCTheme.neutralColors.neutral100
            ),
            onClick = { onClick() },
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}

private fun LazyListScope.movieReviewList(
    pagingData: LazyPagingItems<ItemMovieReviewsResponse>,
) {
    val loadState = pagingData.loadState
    val itemCount = pagingData.itemCount

    when {
        loadState.refresh is LoadState.Loading ->
            items(count = 3) {
                MovieReviewItemWrapper(isShimmer = true,)
            }
        itemCount == 0 ->
            item {
                MCEmptyUIState(
                    modifier = Modifier.fillParentMaxWidth(),
                    description = stringResource(RD.string.empty_review)
                )
            }
        else ->
            items(itemCount) { index ->
                pagingData[index]?.let {
                    MovieReviewItemWrapper(
                        item = it,
                        isShimmer = false,
                    )
                }
            }
    }

    item { MCLoadMoreUIState(loadState = loadState, retry = { pagingData.retry() }) }
    item { Spacer(modifier = Modifier.height(8.dp)) }
}

@Composable
private fun LazyItemScope.MovieReviewItemWrapper(
    item: ItemMovieReviewsResponse? = null,
    isShimmer: Boolean,
) {
    Column(
        modifier = Modifier.fillParentMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        if (!isShimmer && item != null) {
            MovieReviewItem(item = item)
        } else {
            MovieReviewItemShimmer()
        }
    }
}

@Composable
private fun MovieReviewItem(
    item: ItemMovieReviewsResponse
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.authorDetail?.username ?: "-",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MCTheme.primaryColors.primary700,
            style = MCTheme.typography.textMediumM.merge(
                TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        )
        item.authorDetail?.rating?.let {
            Icon(
                modifier = Modifier.size(REVIEW_RATING_ICON_SIZE).padding(horizontal = 4.dp),
                painter = painterResource(id = MCIcons.icRating),
                tint = MCTheme.accentColors.orange500,
                contentDescription = null
            )
            Text(
                text = "$it/10",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MCTheme.primaryColors.neutral700,
                style = MCTheme.typography.textMediumM.merge(
                    TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
            )
        }
    }
    Text(
        text = item.content ?: "-",
        style = MCTheme.typography.textMediumR.copy(
            color = MCTheme.primaryColors.neutral700
        )
    )
}

@Composable
private fun MovieReviewItemShimmer() {
    Row {
        MCRectangleShimmer(width = 100.dp)
        Icon(
            modifier = Modifier.size(REVIEW_RATING_ICON_SIZE).padding(horizontal = 4.dp),
            painter = painterResource(id = MCIcons.icRating),
            tint = MCTheme.accentColors.orange500,
            contentDescription = null
        )
        MCRectangleShimmer(width = 50.dp)
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(IntUtils.COMMON_RADIUS.dp))
            .background(brush = shimmerBrush())
    )
}