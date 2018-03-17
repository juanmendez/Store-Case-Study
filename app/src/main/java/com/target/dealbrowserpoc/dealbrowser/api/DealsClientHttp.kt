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

                /**
                 * a quick fix, the image url is the same for all. So all images are displaying the
                 * same image. I used instead the ones that came with the original demo.
                 */
                val imageResLength = 11
                dealList.forEachIndexed{ index:Int, deal:Deal ->
                        deal.image = "drawable/image${index % imageResLength}"


                        //make up a sales item
                        if( index == 0 ){
                            deal.salesPrice = "$100.00"
                        }
                }

                dealCall.onResponse( dealList )
            }
        })
    }
}