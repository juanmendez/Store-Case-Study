package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.ui.FragmentBuilder.Companion.create
import info.juanmendez.shoeboxes.ShoeStorage
import info.juanmendez.shoeboxes.shoes.ShoeBox
import info.juanmendez.shoeboxes.shoes.ShoeRack
import info.juanmendez.shoeboxes.shoes.ShoeStack
import org.androidannotations.annotations.EBean

/**
 * Created by juan on 3/12/18.
 */
@EBean
class MainPresenter {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var rack:ShoeRack

    fun setView( activity: AppCompatActivity ){
        mActivity = activity

        rack = ShoeStorage.getRack( mActivity::class.java.name )
        val doublePane = mActivity.resources.getBoolean(R.bool.doublePane)
        val fm = mActivity.supportFragmentManager;

        val listBox: ShoeBox = create( fm,rack, R.id.deal_list )
        val itemBox: ShoeBox = create( fm,rack, R.id.deal_item )

        if( doublePane ){
            rack.populate( listBox, itemBox )
        }else{
            rack.populate( ShoeStack.build( listBox, itemBox ))
        }
    }

}