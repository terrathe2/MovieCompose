package com.redhaputra.moviedetail

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.redhaputra.designsystem.component.MCDefaultTopBarContent
import com.redhaputra.model.data.MovieData
import com.redhaputra.navigation.constants.MovieDetailConstant.MOVIE_DATA_KEY
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
    HandleData(navController = navController, setMovieData = viewModel::setMovieData)

    MovieDetailScreen(
        onBackClick = onBackClick
    )
}

@Composable
private fun HandleData(
    navController: NavHostController,
    setMovieData: (MovieData) -> Unit
) {
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val movieData = savedStateHandle?.get<MovieData>(MOVIE_DATA_KEY)

    LaunchedEffect(movieData) {
        movieData?.let {
            setMovieData(it)
            // removing movie data after set, so the data will not persist in saveState
            savedStateHandle.remove<MovieData>(MOVIE_DATA_KEY)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MovieDetailScreen(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MCDefaultTopBarContent(
                title = stringResource(id = RD.string.movie_detail),
                onBackClick = onBackClick
            )
        },
    ) {

    }
}