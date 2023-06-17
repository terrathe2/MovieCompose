package com.redhaputra.ui

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.redhaputra.ui.utils.StringUtils.X_ICON

/**
 * Movie compose reusable component for paging error handler
 */
@Composable
fun MCPagingErrorHandler(
    loadState: CombinedLoadStates,
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit
) {
    val error = when {
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        else -> null
    }
    error?.let {
        LaunchedEffect(it.error) {
            showSnackBar(it.error.message.toString(), X_ICON, SnackbarDuration.Short, null)
        }
    }
}