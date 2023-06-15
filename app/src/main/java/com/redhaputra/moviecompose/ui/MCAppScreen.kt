package com.redhaputra.moviecompose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.redhaputra.designsystem.MCTheme
import com.redhaputra.moviecompose.navigation.MCNavHost
import com.redhaputra.navigation.MCAppState
import com.redhaputra.navigation.rememberMCAppState

/**
 * First screen when starting the app / main screen of the app
 *
 * @param appState: App State of the app, that used to create & config navigation
 */
@Composable
fun MCAppScreen(
    appState: MCAppState = rememberMCAppState(),
) {
    MCTheme {
        Scaffold(
            scaffoldState = appState.scaffoldState,
            snackbarHost = {
                SnackbarHost(hostState = it) { data ->
                    Snackbar(
                        snackbarData = data,
                        actionColor = Color.White,
                        backgroundColor = MCTheme.accentColors.red700
                    )
                }
            }
        ) { innerPadding ->
            MCNavHost(
                modifier = Modifier.padding(innerPadding),
                appState = appState
            )
        }
    }
}