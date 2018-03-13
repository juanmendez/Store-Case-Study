package com.target.dealbrowserpoc.dealbrowser.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.navigation.NavBuilder.Companion.create
import info.juanmendez.shoeboxes.ShoeStorage
import info.juanmendez.shoeboxes.shoes.ShoeBox
import info.juanmendez.shoeboxes.shoes.ShoeRack
import info.juanmendez.shoeboxes.shoes.ShoeStack
import org.androidannotations.annotations.EBean
import java.util.*

/**
 * Created by juan on 3/12/18.
 */
@EBean
class MainPresenter : LifecycleObserver, Observer {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var rack:ShoeRack

    fun setView( activity: AppCompatActivity ){
        mActivity = activity
        mActivity.lifecycle.addObserver( this )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        rack = ShoeStorage.getRack( mActivity::class.java.name )

        val fm = mActivity.supportFragmentManager;

        val listBox: ShoeBox = create( fm,rack, R.id.deal_list )
        val itemBox: ShoeBox = create( fm,rack, R.id.deal_item )

        if( isDoublePane() ){
            rack.populate( listBox, itemBox )
        }else{
            rack.populate( ShoeStack.build( listBox, itemBox ))
        }
        rack.suggest( R.id.deal_list )
        rack.addObserver( this )
        update( null, null )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        rack.onActivityPause()
        rack.deleteObserver( this )
    }

    fun onBackPressed(): Boolean = rack.goBack()

    /**
     * executead upon route update
     */
    override fun update(observable: Observable?, any: Any?) {
        if( rack.history.isNotEmpty() ){
            //pull the last route
            val last = rack.history.last()

            if( !isDoublePane() ){
                displayBackhome( last.indexOf( R.id.deal_item.toString() ) == 0 )
            }else{
                displayBackhome( false )
            }
        }
    }

    private fun isDoublePane():Boolean{
        return mActivity.resources.getBoolean( R.bool.doublePane )
    }

    private fun displayBackhome( show:Boolean ){
        if ( mActivity.getSupportActionBar() != null) {
            mActivity.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(show)
        }
    }
}