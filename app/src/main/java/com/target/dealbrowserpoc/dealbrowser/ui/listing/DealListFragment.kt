package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.support.v4.app.Fragment
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.navigation.NavFragment
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment

/**
 * Created by juan on 3/12/18.
 */
@EFragment(R.layout.fragment_deal_list)
class DealListFragment: Fragment(), NavFragment {

    @Bean
    lateinit var mPresenter: DealListPresenter

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    override fun active(route: String?) {

    }

    override fun inactive() {

    }

    @Click(R.id.buttonNav)
    fun onButtonNav(){
        ShoeStorage.getCurrentRack().request( R.id.deal_item.toString() + "/1" )
    }
}