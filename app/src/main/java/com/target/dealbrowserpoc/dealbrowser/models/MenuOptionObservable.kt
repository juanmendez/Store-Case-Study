package com.target.dealbrowserpoc.dealbrowser.models

import java.util.*

/**
 * Created by juan on 3/17/18.
 */
class MenuOptionObservable: Observable() {

    fun update( option:Int ){
        setChanged()
        notifyObservers( option )
    }
}