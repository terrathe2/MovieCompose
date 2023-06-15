package com.redhaputra.genres

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.redhaputra.designsystem.MCTheme

/**
 * Composable method to handle GenreRoute
 */
@Composable
fun GenreRoute(
    showSnackBar: (String, String?, SnackbarDuration, (() -> Unit)?) -> Unit,
) {
    GenreScreen()
}

@Composable
private fun GenreScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MCTheme {
        Greeting("Android")
    }
}