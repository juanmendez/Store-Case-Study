package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.target.dealbrowserpoc.dealbrowser.BR
import com.target.dealbrowserpoc.dealbrowser.models.Deal

/**
 * Created by juan on 3/19/18.
 */
class DealsObservable:BaseObservable() {
    private var _deals: List<Deal> = listOf()

    var deals: List<Deal>
        @Bindable get() = _deals
        set(value) {
            _deals = value
            notifyPropertyChanged( BR.deals )
        }
}