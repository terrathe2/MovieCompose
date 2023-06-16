package com.redhaputra.genres

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.redhaputra.data.utils.IntUtils.CARD_ELEVATION
import com.redhaputra.data.utils.IntUtils.COMMON_RADIUS
import com.redhaputra.designsystem.R as RD
import com.redhaputra.designsystem.component.MCTopBar
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.theme.MCTheme
import com.redhaputra.genres.state.MovieGenreListState
import com.redhaputra.genres.state.MovieGenresUIState
import com.redhaputra.model.ItemMovieGenresResponse
import com.redhaputra.ui.MCEmptyState

/**
 * Composable method to handle GenreRoute
 */
@Composable
fun GenreRoute(
    viewModel: GenreViewModel = hiltViewModel(),
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    navigateToMovieList: (String) -> Unit,
) {
    val movieGenresUIState by viewModel.movieGenresUiState.collectAsStateWithLifecycle()

    HandleErrorState(
        errorMsg = movieGenresUIState.errorState,
        resetErrorMsg = viewModel::resetErrorMessage,
        showSnackBar = showSnackBar
    )

    GenreScreen(
        movieGenresUIState = movieGenresUIState,
        navigateToMovieList = navigateToMovieList
    )
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
private fun GenreScreen(
    movieGenresUIState: MovieGenresUIState,
    navigateToMovieList: (String) -> Unit
) {
    Scaffold(
        topBar = {
            MCTopBar(screenTitle = stringResource(id = RD.string.movie_genre))
        }
    ) {
        ListMovieGenres(
            movieGenresUIState = movieGenresUIState,
            navigateToMovieList =  navigateToMovieList
        )
    }
}

/**
 * Composable method for list available task
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListMovieGenres(
    movieGenresUIState: MovieGenresUIState,
    navigateToMovieList: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(color = MCTheme.primaryColors.neutral100),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        when (movieGenresUIState.genresState) {
            is MovieGenreListState.Success -> {
                val listGenre = movieGenresUIState.genresState.genres
                listGenre.forEachIndexed { index, item ->
                    ListMovieGenreItem(
                        item = item,
                        navigateToMovieList = { item.name?.let { navigateToMovieList(it) } }
                    )

                    // bottom margin
                    if (index == listGenre.lastIndex) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
            else -> {
                MCEmptyState(
                    modifier = Modifier.padding(vertical = 35.dp),
                    description = stringResource(id = RD.string.empty_movie_genre)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListMovieGenreItem(
    item: ItemMovieGenresResponse,
    navigateToMovieList: () -> Unit
) {
    Card(
        modifier = Modifier.wrapContentWidth(),
        shape = RoundedCornerShape(COMMON_RADIUS.dp),
        elevation = CARD_ELEVATION.dp,
        onClick = navigateToMovieList
    ) {
        Row(
            modifier = Modifier.wrapContentWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.name ?: "-",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MCTheme.primaryColors.primary700,
                style = MCTheme.typography.textMediumM
            )
            Icon(
                modifier = Modifier.padding(start = 8.dp).size(16.dp),
                painter = painterResource(id = MCIcons.icArrowRightIos),
                tint = MCTheme.primaryColors.primary700,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MCTheme {
        GenreScreen(
            MovieGenresUIState(
                MovieGenreListState.Empty,
                ""
            ),
            {}
        )
    }
}