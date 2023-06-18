package com.redhaputra.listmovie.navigation

import android.net.Uri
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.redhaputra.ui.utils.IntUtils.NAV_ANIM_DURATION
import com.redhaputra.listmovie.ListMovieRoute
import com.redhaputra.listmovie.navigation.ListMovieDestination.genreIdArg
import com.redhaputra.listmovie.navigation.ListMovieDestination.genreNameArg
import com.redhaputra.navigation.MCNavigationDestination

/**
 * object that define list movie route & destination
 */
object ListMovieDestination : MCNavigationDestination {
    const val genreIdArg = "genreId"
    const val genreNameArg = "genreName"
    override val route: String = "list_movie_page_route/{$genreIdArg}?name={$genreNameArg}"
    override val destination: String = "list_movie_page_destination"

    /**
     * Creates destination route with genre
     */
    fun createNavigationRoute(genreIdArg: Int, genreNameArg: String?): String {
        val encodedGenreIdSource = Uri.encode(genreIdArg.toString())
        val encodedGenreNameSource = if (genreNameArg.isNullOrEmpty()) {
            "Movie List"
        } else {
            Uri.encode(genreNameArg)
        }
        return "list_movie_page_route/$encodedGenreIdSource?name=$encodedGenreNameSource"
    }
}

/**
 * List movie graph builder config
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.listMovieGraph(
    navController: NavHostController,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
    onBackClick: () -> Unit,
    navigateToMovieDetail: () -> Unit
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
            navArgument(genreIdArg) { type = NavType.StringType },
            navArgument(genreNameArg) { type = NavType.StringType }
        )
    ) {
        ListMovieRoute(
            showSnackBar = showSnackBar,
            navController = navController,
            onBackClick = onBackClick,
            navigateToMovieDetail = navigateToMovieDetail,
        )
    }
}