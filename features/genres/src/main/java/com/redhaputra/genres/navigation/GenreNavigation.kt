package com.redhaputra.genres.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.redhaputra.genres.GenreRoute
import com.redhaputra.navigation.MCNavigationDestination

/**
 * object that define genre route & destination
 */
object GenreDestination : MCNavigationDestination {
    override val route: String = "genre_page_route"
    override val destination: String = "genre_page_destination"
}

/**
 * Genre graph builder config
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.genreGraph(
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    navigateToMovieList: (String) -> Unit,
) {
    composable(route = GenreDestination.route) {
        GenreRoute(
            showSnackBar = showSnackBar,
            navigateToMovieList = navigateToMovieList
        )
    }
}