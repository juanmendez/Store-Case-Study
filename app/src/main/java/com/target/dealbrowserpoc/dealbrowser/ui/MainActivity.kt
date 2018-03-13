package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    /*@Bean
    lateinit var http:DealsClientHttp*/

    @Bean
    lateinit var mainPresenter: MainPresenter

    @AfterViews
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
}