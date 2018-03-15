package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.arch.lifecycle.Lifecycle
import com.target.dealbrowserpoc.dealbrowser.api.DealsCall
import com.target.dealbrowserpoc.dealbrowser.api.DealsClientHttp
import com.target.dealbrowserpoc.dealbrowser.models.Deal
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import com.target.dealbrowserpoc.dealbrowser.ui.utils.ConfirmDialog
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

/**
 * Created by juan on 3/13/18.
 */
@EBean
class DealListPresenter():NavFragment {

    @Bean
    lateinit var http: DealsClientHttp

    private lateinit var mView:DealListFragment

    fun register( view: DealListFragment){
        mView = view
    }

    override fun active(route: String?) {
        val list =  mView.getMainViewModel().liveDealList.value

        if( list?.isEmpty() != false  ){
            refresh()
        }
    }

    override fun inactive() {

    }

    fun refresh(){
        if( !mView.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED))
            return

        /**
         * Once fetching deals, it's required to update MainViewModel's liveDealList
         */
        http.getDeals( object: DealsCall<List<Deal>> {
            override fun onResponse(response: List<Deal>) {
                mView.getMainViewModel().liveDealList.value = response
            }

            override fun onError(exception: Exception) {
                exception.message?.let {
                    ConfirmDialog.newInstance( mView.context, it, {}, true ).show()
                }
            }
        })
    }
}