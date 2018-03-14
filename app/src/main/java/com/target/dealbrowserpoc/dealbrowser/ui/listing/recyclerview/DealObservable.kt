package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.target.dealbrowserpoc.dealbrowser.BR
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal

/**
 * Created by juan on 3/14/18.
 */
class DealObservable:BaseObservable() {
    private lateinit var _deal:Deal

    var deal:Deal
        @Bindable get() = _deal
        set(value) {
            _deal = value
            notifyPropertyChanged( BR.dealObservable )
        }
}