package com.redhaputra.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.redhaputra.designsystem.theme.MCIcons
import com.redhaputra.designsystem.R as RD
import com.redhaputra.designsystem.theme.MCTheme

/**
 * Movie compose paging load more ui state reusable component
 *
 * @param modifier: is for loading progress indicator modifier
 * @param loadMoreModifier: is for load more error modifier
 * @param loadState: list load state needed
 * @param retry: retry method if fetch failed
 */
@Composable
fun MCLoadMoreUIState(
    modifier: Modifier = Modifier,
    loadMoreModifier: Modifier = Modifier,
    loadState: CombinedLoadStates,
    retry: () -> Unit,
) {
    when (loadState.append) {
        is LoadState.Loading ->
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        is LoadState.Error ->
            MCLoadMoreError(
                modifier = loadMoreModifier.padding(bottom = 16.dp),
                clickAction = retry
            )
        else -> Unit
    }
}

@Composable
private fun MCLoadMoreError(
    modifier: Modifier = Modifier,
    clickAction: () -> Unit
) {
    Row(
        modifier = modifier.clickable { clickAction.invoke() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = MCIcons.icCached),
            tint = MCTheme.primaryColors.primary700,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = RD.string.click_to_retry),
            style = MCTheme.typography.textLargeM,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}