package com.target.dealbrowserpoc.dealbrowser

import android.app.Application
import org.androidannotations.annotations.EApplication
import timber.log.BuildConfig
import timber.log.Timber

/**
 * Created by juan on 3/12/18.
 */
@EApplication
class DealsApp:Application() {

    override fun onCreate() {
        super.onCreate()
        handleTimber()
    }

    private fun handleTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}