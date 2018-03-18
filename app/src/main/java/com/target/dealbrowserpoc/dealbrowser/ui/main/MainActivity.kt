package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.*

@SuppressLint("Registered")
@OptionsMenu(R.menu.menu)
@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    private val rack = ShoeStorage.getRack( this::class.java.name )

    @AfterInject
    fun afterViews(){

        /**
         * MainNavigation encapsulates functionality for Fragment Navigation
         * And is not reached or used by our presenter, therefore it can
         * be simply instantiated right inside the Activity
         */
        MainNavigation( this, rack )
    }

    @OptionsItem(android.R.id.home)
    fun onBackHomeOption(){
        onBackPressed()
    }

    @OptionsItem(R.id.menu_refresh)
    fun onMenuRefresh(){
        val vm = ViewModelProviders.of( this ).get( MainViewModel::class.java )
        vm.menuOptionObservable.update( R.id.menu_refresh )
    }

    override fun onBackPressed() {
        if( !rack.goBack()){
            super.onBackPressed()
        }
    }
}