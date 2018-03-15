package com.target.dealbrowserpoc.dealbrowser.api

import com.target.dealbrowserpoc.dealbrowser.models.DealsResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by juan on 3/12/18.
 */
interface DealsAPI {
    @GET(DealsRoute.ALL_DEALS)
    fun getDealsResponse(): Call<DealsResponse>
}