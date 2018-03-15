package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.target.dealbrowserpoc.dealbrowser.BR
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal

/**
 * Created by juan on 3/14/18.
 * Databinding for deal associated with its view
 */
class DealObservable:BaseObservable() {
    private var _deal:Deal? = null

    var deal:Deal?
        @Bindable get() = _deal
        set(value) {
            _deal = value
            notifyPropertyChanged( BR.deal )
        }
}