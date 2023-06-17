package com.redhaputra.ui.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems

/**
 * Workaround of lazy list bug with paging 3 collectAsLazyPagingItems
 *
 * Bug: After recreation, LazyPagingItems first return 0 items, then the cached items.
 * This behavior/issue is resetting the LazyListState scroll position.
 * Below is a workaround.
 *
 * More info: https://issuetracker.google.com/issues/177245496.
 */
@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState =
    if (itemCount == 0) {
        // Return a different LazyListState instance.
        remember(this) { LazyListState(0, 0) }
    } else {
        // Return rememberLazyListState (normal case).
        androidx.compose.foundation.lazy.rememberLazyListState()
    }