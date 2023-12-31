package com.redhaputra.genres.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.redhaputra.genres.GenreRoute
import com.redhaputra.navigation.MCNavigationDestination
import com.redhaputra.ui.utils.IntUtils.NAV_ANIM_DURATION

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
    navigateToMovieList: (Int, String?) -> Unit,
) {
    composable(
        route = GenreDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(NAV_ANIM_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(NAV_ANIM_DURATION)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(NAV_ANIM_DURATION)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(NAV_ANIM_DURATION)
            )
        }
    ) {
        GenreRoute(
            showSnackBar = showSnackBar,
            navigateToMovieList = navigateToMovieList
        )
    }
}