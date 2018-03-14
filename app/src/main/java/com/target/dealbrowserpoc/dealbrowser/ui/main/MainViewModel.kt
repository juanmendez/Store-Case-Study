package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal

/**
 * Created by juan on 3/13/18.
 */
class MainViewModel: ViewModel() {
    val liveDealSelected:MutableLiveData<Deal> = MutableLiveData()
    val liveDealList:MutableLiveData<List<Deal>> = MutableLiveData()
}