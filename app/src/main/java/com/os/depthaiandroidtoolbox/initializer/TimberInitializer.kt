package com.os.depthaiandroidtoolbox.initializer

import android.content.Context
import androidx.startup.Initializer
import com.os.depthaiandroidtoolbox.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber Initializer is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}