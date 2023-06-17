package com.redhaputra.listmovie

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.redhaputra.designsystem.component.MCDefaultTopBarContent
import com.redhaputra.ui.MCPagingErrorHandler

/**
 * Composable method to handle ListMovieRoute
 */
@Composable
fun ListMovieRoute(
    viewModel: ListMovieViewModel = hiltViewModel(),
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
    navigateToMovieDetail: (String) -> Unit,
) {
    val movieList = viewModel.movieList.collectAsLazyPagingItems()

    ListMovieScreen(
        genre = viewModel.genre,
        onBackClick = onBackClick,
        navigateToMovieDetail = navigateToMovieDetail
    )
    MCPagingErrorHandler(loadState = movieList.loadState, showSnackBar = showSnackBar)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ListMovieScreen(
    genre: String,
    onBackClick: () -> Unit,
    navigateToMovieDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            MCDefaultTopBarContent(
                title = genre,
                onBackClick = onBackClick
            )
         },
    ) {
    }
}