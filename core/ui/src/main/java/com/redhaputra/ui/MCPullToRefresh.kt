package com.redhaputra.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.redhaputra.designsystem.theme.MCTheme

/**
 * Movie compose pull to refresh reusable component
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MCPullToRefresh(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(refreshing = false, onRefresh = onRefresh)

    Box(modifier = modifier.pullRefresh(pullRefreshState)) {
        content()
        PullRefreshIndicator(
            refreshing = false,
            state = pullRefreshState,
            contentColor = MCTheme.primaryColors.primary700,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}