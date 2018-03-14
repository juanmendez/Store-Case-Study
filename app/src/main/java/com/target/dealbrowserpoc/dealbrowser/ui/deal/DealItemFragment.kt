package com.target.dealbrowserpoc.dealbrowser.ui.deal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import com.target.dealbrowserpoc.dealbrowser.ui.DealView
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment

/**
 * Created by juan on 3/12/18.
 */
@EFragment(R.layout.fragment_deal_item)
class DealItemFragment: Fragment(), DealView{

    @Bean
    lateinit var mPresenter:DealItemPresenter

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    override fun active(route: String?) = mPresenter.active( route )
    override fun inactive() = mPresenter.inactive()


    override fun getLifeCycle(): Lifecycle =activity.lifecycle

    @Click(R.id.buttonNav)
    fun onButtonNav(){
        ShoeStorage.getCurrentRack().request( R.id.deal_list )
    }

    override fun getMainViewModel(): MainViewModel {
        return ViewModelProviders.of( this ).get( MainViewModel::class.java )
    }
}