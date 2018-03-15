package com.target.dealbrowserpoc.dealbrowser.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel

/**
 * Created by juan on 3/14/18.
 */
interface DealView:NavFragment,LifecycleOwner {
    fun getLifeCycle():Lifecycle
    fun getMainViewModel():MainViewModel
}