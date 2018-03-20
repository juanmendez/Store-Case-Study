package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import org.androidannotations.annotations.*

@SuppressLint("Registered")
@OptionsMenu(R.menu.menu)
@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    private val mMainNavigation = MainNavigation( this )

    @OptionsItem(android.R.id.home)
    fun onHomeClick(){
        onBackPressed()
    }

    @OptionsItem(R.id.menu_refresh)
    fun onMenuRefreshClick(){
        val vm = ViewModelProviders.of( this ).get( MainViewModel::class.java )
        vm.menuOptionObservable.update( R.id.menu_refresh )
    }

    override fun onBackPressed() {
        if( !mMainNavigation.onBackPressed()){
            super.onBackPressed()
        }
    }
}