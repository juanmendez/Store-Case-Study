package com.target.dealbrowserpoc.dealbrowser.navigation

import android.support.v4.app.FragmentManager
import info.juanmendez.shoeboxes.shoes.ShoeBox
import info.juanmendez.shoeboxes.shoes.ShoeRack

/**
 * Created by juan on 3/12/18.
 */
class NavBuilder {
    companion object {
        fun create( fm:FragmentManager, rack:ShoeRack, id:Int ):ShoeBox{
            return ShoeBox.build(NavAdapter(fm.findFragmentById(id), rack))
        }
    }
}