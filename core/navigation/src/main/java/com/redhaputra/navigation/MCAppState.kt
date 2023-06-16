package com.redhaputra.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Composable method to create navigation controller and pass it to [MCAppState]
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberMCAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember {
            SnackbarHostState()
        }
    ),
    navController: NavHostController = rememberAnimatedNavController(),
    snackbarScope: CoroutineScope = rememberCoroutineScope(),
): MCAppState =
    remember(scaffoldState, navController, snackbarScope) {
        MCAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            snackbarScope = snackbarScope
        )
    }

/**
 * Class that used as app state to config navigation controller methods
 *
 * @see Stable
 * @param navController: navigation controller used in the App
 */
@Stable
class MCAppState(
    val scaffoldState: ScaffoldState,
    val snackbarScope: CoroutineScope,
    val navController: NavHostController
) {
    private var snackBarJob: Job? = null

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * @param destination: The [MCNavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
    fun navigate(destination: MCNavigationDestination, route: String? = null) {
        navController.navigate(route ?: destination.route) {
            launchSingleTop = true
        }
    }

    /**
     * Method to handle back
     */
    fun onBackClick() {
        navController.popBackStack()
    }

    /**
     * Handle show snackBar
     */
    fun showSnackBar(
        message: String,
        actionLabel: String? = "X",
        duration: SnackbarDuration = SnackbarDuration.Short,
        snackBarAction: (() -> Unit)? = null
    ) {
        snackbarScope.launch {
            snackBarJob?.cancel()
            snackBarJob = launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel ?: "X",
                    duration = duration
                )
                when (snackBarResult) {
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed ->
                        snackBarAction
                            ?: scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
        }
    }
}