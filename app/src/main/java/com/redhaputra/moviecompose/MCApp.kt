package com.redhaputra.moviecompose

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

/**
 * Base class for maintaining global application state.
 *
 * @see Application
 */
@HiltAndroidApp
class MCApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initStetho()
        }
    }

    /**
     *  Network logger initialization
     */
    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}