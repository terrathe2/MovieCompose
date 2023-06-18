package com.redhaputra.moviedetail.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.redhaputra.moviedetail.MovieDetailRoute
import com.redhaputra.navigation.MCNavigationDestination
import com.redhaputra.ui.utils.IntUtils.NAV_ANIM_DURATION

/**
 * object that define movie detail route & destination
 */
object MovieDetailDestination : MCNavigationDestination {
    override val route: String = "movie_detail_page_route"
    override val destination: String = "movie_detail_page_destination"
}

/**
 * Movie detail graph builder config
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieDetailGraph(
    navController: NavHostController,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = MovieDetailDestination.route,
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
        MovieDetailRoute(
            navController = navController,
            showSnackBar = showSnackBar,
            onBackClick = onBackClick,
        )
    }
}