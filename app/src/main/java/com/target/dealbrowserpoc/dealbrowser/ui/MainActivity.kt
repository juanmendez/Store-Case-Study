package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v7.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.api.DealsCall
import com.target.dealbrowserpoc.dealbrowser.api.DealsClientHttp
import com.target.dealbrowserpoc.dealbrowser.api.models.Deals
import com.target.dealbrowserpoc.dealbrowser.ui.utils.ConfirmDialog
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity
import timber.log.Timber

@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @Bean
    lateinit var http:DealsClientHttp

    @AfterViews
    fun afterViews(){
        http.getBreeds( object:DealsCall<List<Deals>>{
            override fun onResponse(response: List<Deals>) {
                Timber.i( "response ${response}")
            }

            override fun onError(exception: Exception) {
                exception?.message?.let {
                    ConfirmDialog.newInstance( this@MainActivity, it, {}, true ).show()
                }
            }
        })
    }
}