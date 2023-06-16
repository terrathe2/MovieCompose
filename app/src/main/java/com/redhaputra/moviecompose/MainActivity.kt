package com.redhaputra.moviecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.redhaputra.moviecompose.ui.MCAppScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base activity class that handle compose features.
 *
 * @see ComponentActivity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MCAppScreen()
        }
    }
}