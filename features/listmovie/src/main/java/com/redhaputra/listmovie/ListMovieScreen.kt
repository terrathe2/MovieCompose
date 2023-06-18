package com.redhaputra.listmovie

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.redhaputra.designsystem.component.MCDefaultTopBarContent
import com.redhaputra.designsystem.component.MCImageFromUrl
import com.redhaputra.designsystem.theme.MCTheme
import com.redhaputra.designsystem.R as RD
import com.redhaputra.model.data.MovieData
import com.redhaputra.navigation.constants.MovieDetailConstant.MOVIE_DATA_KEY
import com.redhaputra.ui.MCEmptyUIState
import com.redhaputra.ui.MCLoadMoreUIState
import com.redhaputra.ui.MCPagingErrorHandler
import com.redhaputra.ui.MCPullToRefresh
import com.redhaputra.ui.MCRectangleShimmer
import com.redhaputra.ui.shimmerBrush
import com.redhaputra.ui.state.rememberLazyListState
import com.redhaputra.ui.utils.IntUtils.CARD_ELEVATION
import com.redhaputra.ui.utils.IntUtils.COMMON_RADIUS
import com.redhaputra.ui.utils.StringUtils.toPosterImg
import kotlinx.coroutines.flow.flowOf

private val imgWidth = 100.dp
private val imgHeight = 130.dp

/**
 * Composable method to handle ListMovieRoute
 */
@Composable
fun ListMovieRoute(
    navController: NavHostController,
    viewModel: ListMovieViewModel = hiltViewModel(),
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
    navigateToMovieDetail: () -> Unit,
) {
    val movieList = viewModel.movieList.collectAsLazyPagingItems()
    val currentSaveState = navController.currentBackStackEntry?.savedStateHandle

    ListMovieScreen(
        genre = viewModel.genreName,
        movieList = movieList,
        currentSaveState = currentSaveState,
        onBackClick = onBackClick,
        navigateToMovieDetail = navigateToMovieDetail
    )
    MCPagingErrorHandler(loadState = movieList.loadState, showSnackBar = showSnackBar)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ListMovieScreen(
    genre: String,
    movieList: LazyPagingItems<MovieData>,
    currentSaveState: SavedStateHandle?,
    onBackClick: () -> Unit,
    navigateToMovieDetail: () -> Unit
) {
    Scaffold(
        topBar = {
            MCDefaultTopBarContent(
                title = genre,
                onBackClick = onBackClick
            )
         },
    ) { padding ->
        MCPullToRefresh(
            modifier = Modifier.padding(padding),
            onRefresh = {
                if (movieList.loadState.refresh !is LoadState.Loading) {
                    movieList.refresh()
                }
            }
        ) {
            MovieList(
                pagingData = movieList,
                currentSaveState = currentSaveState,
                navigateToMovieDetail = navigateToMovieDetail
            )
        }
    }
}

/**
 * Composable component for movie list
 */
@Composable
fun MovieList(
    pagingData: LazyPagingItems<MovieData>,
    currentSaveState: SavedStateHandle?,
    navigateToMovieDetail: () -> Unit
) {
    val loadState = pagingData.loadState
    val itemCount = pagingData.itemCount
    val listState = pagingData.rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(8.dp)) }

        when {
            loadState.refresh is LoadState.Loading ->
                items(count = 5) {
                    MovieListItemCard(
                        isShimmer = true,
                        currentSaveState = currentSaveState,
                        navigateToMovieDetail = navigateToMovieDetail
                    )
                }
            itemCount == 0 ->
                item {
                    MCEmptyUIState(
                        modifier = Modifier.fillParentMaxSize(),
                        description = stringResource(RD.string.empty_movie)
                    )
                }
            else ->
                items(itemCount) { index ->
                    pagingData[index]?.let {
                        MovieListItemCard(
                            item = it,
                            isShimmer = false,
                            currentSaveState = currentSaveState,
                            navigateToMovieDetail = navigateToMovieDetail
                        )
                    }
                }
        }

        item { MCLoadMoreUIState(loadState = loadState, retry = { pagingData.retry() }) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LazyItemScope.MovieListItemCard(
    item: MovieData? = null,
    isShimmer: Boolean,
    currentSaveState: SavedStateHandle?,
    navigateToMovieDetail: () -> Unit
) {
    Card(
        modifier = Modifier.fillParentMaxWidth().padding(end = 16.dp),
        shape = RoundedCornerShape(COMMON_RADIUS.dp),
        elevation = CARD_ELEVATION.dp,
        onClick = {
            if (!isShimmer) {
                currentSaveState?.set(MOVIE_DATA_KEY, item)
                navigateToMovieDetail.invoke()
            }
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            if (!isShimmer && item != null) {
                MovieItem(item = item)
            } else {
                MovieItemShimmer()
            }
        }
    }
}

@Composable
private fun MovieItemShimmer() {
    Spacer(
        modifier = Modifier
            .width(imgWidth)
            .height(imgHeight)
            .clip(RoundedCornerShape(COMMON_RADIUS.dp))
            .background(brush = shimmerBrush())
    )
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        MCRectangleShimmer(width = 150.dp)
        MCRectangleShimmer(
            modifier = Modifier.padding(top = 3.dp),
            height = 60.dp,
            width = 150.dp
        )
    }
}

@Composable
private fun MovieItem(
    item: MovieData
) {
    MCImageFromUrl(
        modifier = Modifier
            .width(imgWidth)
            .height(imgHeight)
            .clip(RoundedCornerShape(COMMON_RADIUS.dp))
            .background(color = MCTheme.primaryColors.neutral200),
        imageUrl = item.posterImg.toPosterImg(),
        contentScale = ContentScale.FillBounds,
        contentDesc = null
    )
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = item.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MCTheme.primaryColors.neutral700,
            style = MCTheme.typography.textLargeM
        )
        Text(
            modifier = Modifier.padding(top = 3.dp),
            text = item.overview,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = MCTheme.primaryColors.neutral600,
            style = MCTheme.typography.textMediumR
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListMoviePreview() {
    MCTheme {
        ListMovieScreen(
            genre = "Action",
            movieList = flowOf(PagingData.empty<MovieData>()).collectAsLazyPagingItems(),
            currentSaveState = null,
            onBackClick = {},
            navigateToMovieDetail = {}
        )
    }
}