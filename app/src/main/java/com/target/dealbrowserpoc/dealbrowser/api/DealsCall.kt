package com.target.dealbrowserpoc.dealbrowser.api

/**
 * Created by juan on 3/12/18.
 * Rather than having a direct relationship with view and retrofit, this interface
 * is used as http client could change.
 */
interface DealsCall<T> {
    fun onResponse( response:T )
    fun onError( exception: Exception )
}