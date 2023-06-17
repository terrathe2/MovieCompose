package com.redhaputra.listmovie.navigation

import android.net.Uri
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.redhaputra.data.utils.IntUtils.NAV_ANIM_DURATION
import com.redhaputra.listmovie.ListMovieRoute
import com.redhaputra.navigation.MCNavigationDestination

/**
 * object that define list movie route & destination
 */
object ListMovieDestination : MCNavigationDestination {
    const val genreArg = "genre"
    override val route: String = "list_movie_page_route/{$genreArg}"
    override val destination: String = "list_movie_page_destination"

    /**
     * Creates destination route with genre
     */
    fun createNavigationRoute(genreArg: String): String {
        val encodedSource = if (genreArg.isEmpty()) {
            "-"
        } else {
            Uri.encode(genreArg)
        }
        return "list_movie_page_route/$encodedSource"
    }
}

/**
 * List movie graph builder config
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.listMovieGraph(
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
    navigateToMovieDetail: (String) -> Unit
) {
    composable(
        route = ListMovieDestination.route,
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
        },
        arguments = listOf(
            navArgument(ListMovieDestination.genreArg) { type = NavType.StringType }
        )
    ) {
        ListMovieRoute(
            showSnackBar = showSnackBar,
            onBackClick = onBackClick,
            navigateToMovieDetail = navigateToMovieDetail,
        )
    }
}