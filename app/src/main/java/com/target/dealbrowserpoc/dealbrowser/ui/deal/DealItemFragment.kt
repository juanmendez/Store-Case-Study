package com.target.dealbrowserpoc.dealbrowser.ui.deal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDealItemBinding
import com.target.dealbrowserpoc.dealbrowser.ui.DealView
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.*

/**
 * Created by juan on 3/12/18.
 */
@DataBound
@EFragment(R.layout.fragment_deal_item)
class DealItemFragment: Fragment(), DealView{

    @Bean
    lateinit var mPresenter:DealItemPresenter

    @BindingObject
    lateinit var binding:FragmentDealItemBinding

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    override fun active(route: String?) = mPresenter.active( route )
    override fun inactive() = mPresenter.inactive()


    override fun getLifeCycle(): Lifecycle = activity.lifecycle

    /*@Click(R.id.buttonNav)
    fun onButtonNav(){
        ShoeStorage.getCurrentRack().request( R.id.deal_list )
    }*/

    override fun getMainViewModel(): MainViewModel {
        return ViewModelProviders.of( activity ).get( MainViewModel::class.java )
    }
}