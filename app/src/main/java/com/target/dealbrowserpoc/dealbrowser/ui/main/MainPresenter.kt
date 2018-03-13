package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.arch.lifecycle.LifecycleObserver
import android.support.v7.app.AppCompatActivity
import org.androidannotations.annotations.EBean

/**
 * Created by juan on 3/12/18.
 */
@EBean
class MainPresenter : LifecycleObserver {

    private lateinit var mActivity: AppCompatActivity

    fun setView( activity: AppCompatActivity ){
        mActivity = activity
        mActivity.lifecycle.addObserver( this )
    }
}