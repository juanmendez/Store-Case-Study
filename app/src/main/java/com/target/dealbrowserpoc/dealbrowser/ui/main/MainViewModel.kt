package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.target.dealbrowserpoc.dealbrowser.models.Deal

/**
 * Created by juan on 3/13/18.
 */
class MainViewModel: ViewModel() {

    val liveDealSelected:MutableLiveData<Deal> = MutableLiveData()
    val liveDealList:MutableLiveData<List<Deal>> = MutableLiveData()
    val menuOptionObservable = MenuOptionObservable() //don't update future subscribers like LiveData does
}