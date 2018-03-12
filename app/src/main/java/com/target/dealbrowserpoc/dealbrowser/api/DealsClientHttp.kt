package com.target.dealbrowserpoc.dealbrowser.api

import com.target.dealbrowserpoc.dealbrowser.api.models.Deals
import com.target.dealbrowserpoc.dealbrowser.api.models.DealsResponse
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

    fun getBreeds(dealsCall: DealsCall<List<Deals>>){
        var call:Call<DealsResponse> = mDealsApi.getDealsResponse()

        call.enqueue( object:Callback<DealsResponse>{
            override fun onFailure(call: Call<DealsResponse>?, t: Throwable?) {
                dealsCall.onError( Exception( t?.message ?: "${DealsRoute.ALL_DEALS}Error") )
            }

            override fun onResponse(call: Call<DealsResponse>?, response: Response<DealsResponse>?) {
                val breedList = response!!.body().data
                dealsCall.onResponse( breedList )
            }
        })
    }
}