package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v4.app.FragmentManager
import info.juanmendez.shoeboxes.shoes.ShoeBox
import info.juanmendez.shoeboxes.shoes.ShoeRack

/**
 * Created by juan on 3/12/18.
 */
class FragmentBuilder {
    companion object {
        fun create( fm:FragmentManager, rack:ShoeRack, id:Int ):ShoeBox{
            return ShoeBox.build( ShoeBoxAdapter( fm.findFragmentById(id), rack ) )
        }
    }
}