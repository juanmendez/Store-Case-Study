package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.navigation.NavBuilder
import info.juanmendez.shoeboxes.shoes.ShoeBox
import info.juanmendez.shoeboxes.shoes.ShoeRack
import info.juanmendez.shoeboxes.shoes.ShoeStack
import java.util.*

/**
 * Created by juan on 3/13/18.
 * 0. Takes care of defining fragment structure in MainActivity
 * 1. Listens for navigation updates
 * 2. Updates toolbar based on those navigation updates
 * 3. Updates ShoeRack upon activity pause
 *
 */
class MainNavigation(private var mActivity:AppCompatActivity, private var rack: ShoeRack) : LifecycleObserver, Observer {

    init {
        mActivity.lifecycle.addObserver( this )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        val fm = mActivity.supportFragmentManager

        //0
        val listBox: ShoeBox = NavBuilder.create(fm, rack, R.id.deal_list)
        val itemBox: ShoeBox = NavBuilder.create(fm, rack, R.id.deal_item)

        if( isDoublePane() ){
            rack.populate( listBox, itemBox )
        }else{
            rack.populate( ShoeStack.build( listBox, itemBox ))
        }
        rack.suggest( R.id.deal_list )

        //1
        rack.addObserver( this )
        update( null, null )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        //3
        rack.onActivityPause()
        rack.deleteObserver( this )
    }

    /**
     * executead upon route update we can update the toolBar based on navigation
     */
    override fun update(observable: Observable?, any: Any?) {
        //2
        if( rack.history.isNotEmpty() ){
            //pull the last route
            val last = rack.history.last()
            val isDealItemActive = last.indexOf( R.id.deal_item.toString() ) == 0

            if( !isDoublePane() ){
                displayBackhome( isDealItemActive )

                /**
                 * In this demo we have one menu item. So we want it to display whenever
                 * the deals-fragment is displayed
                 */
                mActivity.findViewById<View>( R.id.menu_refresh )?.let{
                    if( isDealItemActive ){
                        it.visibility = View.GONE
                    }else{
                        it.visibility = View.VISIBLE
                    }
                }

            }else{
                displayBackhome( false )
                mActivity.findViewById<View>( R.id.menu_refresh )?.visibility = View.VISIBLE
            }
        }
    }

    private fun isDoublePane():Boolean{
        return mActivity.resources.getBoolean( R.bool.doublePane )
    }

    /**
     * Displays back button at the toolBar
     */
    private fun displayBackhome( show:Boolean ){
        if ( mActivity.getSupportActionBar() != null) {
            mActivity.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(show)
        }
    }
}