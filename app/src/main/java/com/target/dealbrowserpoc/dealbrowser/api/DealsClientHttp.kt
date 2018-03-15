package com.target.dealbrowserpoc.dealbrowser.api

import com.target.dealbrowserpoc.dealbrowser.models.Deal
import com.target.dealbrowserpoc.dealbrowser.models.DealsResponse
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Juan Mendez on 3/12/18.
 */
@EBean
class DealsClientHttp {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mDealsApi: DealsAPI

    @AfterInject
    fun afterInject(){
        mRetrofit = Retrofit
                        .Builder()
                        .baseUrl(DealsRoute.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                    .build()

        mDealsApi = mRetrofit.create( DealsAPI::class.java)
    }

    fun getDeals(dealCall: DealsCall<List<Deal>>){
        var call:Call<DealsResponse> = mDealsApi.getDealsResponse()

        call.enqueue( object:Callback<DealsResponse>{
            override fun onFailure(call: Call<DealsResponse>?, t: Throwable?) {
                dealCall.onError( Exception( t?.message ?: "${DealsRoute.ALL_DEALS}Error") )
            }

            override fun onResponse(call: Call<DealsResponse>?, response: Response<DealsResponse>?) {
                val dealList = response!!.body().data
                dealCall.onResponse( dealList )
            }
        })
    }
}