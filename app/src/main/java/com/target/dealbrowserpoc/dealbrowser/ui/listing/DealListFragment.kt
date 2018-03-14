package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.ui.DealView
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.*

/**
 * Created by juan on 3/12/18.
 */
@OptionsMenu(R.menu.menu)
@EFragment(R.layout.fragment_deal_list)
class DealListFragment: Fragment(), DealView {
    @Bean
    lateinit var mPresenter: DealListPresenter

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    override fun active(route: String?)=mPresenter.active( route )

    override fun inactive()=mPresenter.inactive()

    override fun getLifeCycle(): Lifecycle=activity.lifecycle

    @Click(R.id.buttonNav)
    fun onButtonNav(){
        ShoeStorage.getCurrentRack().request( R.id.deal_item.toString() + "/1" )
    }

    override fun getMainViewModel(): MainViewModel {
        return ViewModelProviders.of( this ).get( MainViewModel::class.java )
    }


    @OptionsItem(R.id.menu_refresh)
    fun onMenuRefresh(){
        mPresenter.refresh()
    }
}