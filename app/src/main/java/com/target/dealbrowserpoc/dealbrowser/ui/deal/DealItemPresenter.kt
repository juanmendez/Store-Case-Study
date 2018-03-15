package com.target.dealbrowserpoc.dealbrowser.ui.deal

import android.arch.lifecycle.Observer
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview.DealObservable
import org.androidannotations.annotations.EBean

/**
 * Created by juan on 3/13/18.
 * Ensures to upate its view to the last selected deal
 */
@EBean
class DealItemPresenter():NavFragment {
    private lateinit  var mView:DealItemFragment

    fun register( view: DealItemFragment ){
        mView = view
    }

    override fun active(route: String?) {

        /**
         * Using the view's binding, we are able to
         * find the last selected Deal found in the list, and
         * then we update the binding based on that element
         */
        mView.binding.dealObservable = DealObservable()

        mView.getMainViewModel().liveDealSelected.observe( mView, Observer {
            it?.let {
                mView.binding.dealObservable?.deal = it
            }
        })
    }

    override fun inactive() {
    }
}