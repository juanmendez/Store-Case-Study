package com.target.dealbrowserpoc.dealbrowser.ui.main

import java.util.*

/**
 * Created by juan on 3/17/18.
 * Since menu is declared in MainActivity, fragments
 * require to get options clicked through this observabled shared between activity, and fragments
 */
class MenuOptionObservable: Observable() {

    fun update( option:Int ){
        setChanged()
        notifyObservers( option )
    }
}