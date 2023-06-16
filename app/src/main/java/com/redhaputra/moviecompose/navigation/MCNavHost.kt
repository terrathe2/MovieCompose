package com.redhaputra.moviecompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.redhaputra.genres.navigation.GenreDestination
import com.redhaputra.genres.navigation.genreGraph
import com.redhaputra.navigation.MCAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 * @param modifier a [Modifier] for parent the text field
 * @param appState App State of the app, that used to create & config navigation
 * @param startDestination default start destination of the navigation
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MCNavHost(
    modifier: Modifier,
    appState: MCAppState,
    startDestination: String = GenreDestination.route
) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        genreGraph(
            showSnackBar = appState::showSnackBar,
            navigateToMovieList = {}
        )
    }
}