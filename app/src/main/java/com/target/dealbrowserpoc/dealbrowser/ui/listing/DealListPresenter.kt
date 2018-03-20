package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.arch.lifecycle.Lifecycle
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.api.DealsCall
import com.target.dealbrowserpoc.dealbrowser.api.DealsClientHttp
import com.target.dealbrowserpoc.dealbrowser.models.Deal
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import com.target.dealbrowserpoc.dealbrowser.ui.utils.ConfirmDialog
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import java.util.*

/**
 * Created by juan on 3/13/18.
 */
@EBean
class DealListPresenter():NavFragment, Observer {

    @Bean
    lateinit var http: DealsClientHttp

    private lateinit var mView:DealListFragment

    fun register( view: DealListFragment){
        mView = view
    }

    override fun active(route: String?) {
        
        val list =  getViewModel().liveDealList.value

        if( list?.isEmpty() != false  ){
            refresh()
        }

        getViewModel().menuOptionObservable.addObserver( this )
    }

    override fun inactive() {
        getViewModel().menuOptionObservable.deleteObserver( this )
    }

    override fun update(p0: Observable?, p1: Any?) {
        if( p1 == R.id.menu_refresh ){
            refresh()
        }
    }

    fun refresh(){
        if( !mView.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED))
            return

        //we want to reload, and therefore empty the listing
        getViewModel().liveDealList.value = mutableListOf()
        mView.onDealsListChange( listOf() )

        /**
         * Once fetching deals, it's required to update MainViewModel's liveDealList
         */
        http.getDeals( object: DealsCall<List<Deal>> {
            override fun onResponse(response: List<Deal>) {
                getViewModel().liveDealList.value = response
                mView.onDealsListChange( response )
                setFirstDealAsDefault()
            }

            override fun onError(exception: Exception) {
                exception.message?.let {
                    ConfirmDialog.newInstance( mView.context, it, {}, true ).show()
                }
            }
        })
    }

    private fun setFirstDealAsDefault(){

        //ensure first deal as default when viewed from double pane
        if( isDoublePane() ){
            val deal = getViewModel().liveDealSelected.value
            val deals = getViewModel().liveDealList.value

            if( deal == null && deals?.isNotEmpty()==true ){
                getViewModel().liveDealSelected.value = deals[0]
                ShoeStorage.getCurrentRack().request( R.id.deal_item )
            }
        }
    }

    private fun isDoublePane():Boolean{
        return mView.resources.getBoolean( R.bool.doublePane )
    }

    private fun getViewModel()=mView.getMainViewModel()
}