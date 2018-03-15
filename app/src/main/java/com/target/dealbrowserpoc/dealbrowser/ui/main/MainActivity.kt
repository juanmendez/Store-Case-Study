package com.target.dealbrowserpoc.dealbrowser.ui.main

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import info.juanmendez.shoeboxes.ShoeStorage
import info.juanmendez.shoeboxes.shoes.ShoeRack
import org.androidannotations.annotations.*

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    /*@Bean
    lateinit var http:DealsClientHttp*/

    @Bean
    lateinit var mainPresenter: MainPresenter

    private lateinit var rack: ShoeRack

    @AfterInject
    fun afterViews(){
        mainPresenter.setView( this )

        rack = ShoeStorage.getRack( this::class.java.name )

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

    override fun onBackPressed() {
        if( !rack.goBack()){
            super.onBackPressed()
        }
    }
}