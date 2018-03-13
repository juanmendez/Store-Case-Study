package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import info.juanmendez.shoeboxes.ShoeStorage
import org.androidannotations.annotations.*

@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    /*@Bean
    lateinit var http:DealsClientHttp*/

    @Bean
    lateinit var mainPresenter: MainPresenter

    @AfterInject
    fun afterViews(){
        mainPresenter.setView( this )
        /*http.getBreeds( object:DealsCall<List<Deals>>{
            override fun onResponse(response: List<Deals>) {
                Timber.i( "response ${response}")
            }

            override fun onError(exception: Exception) {
                exception?.message?.let {
                    ConfirmDialog.newInstance( this@MainActivity, it, {}, true ).show()
                }
            }
        })*/
    }

    @OptionsItem(android.R.id.home)
    fun onBackHomeOption(){
        onBackPressed()
    }

    override fun onBackPressed() {
        if( !mainPresenter.onBackPressed()){
            super.onBackPressed()
        }
    }

}