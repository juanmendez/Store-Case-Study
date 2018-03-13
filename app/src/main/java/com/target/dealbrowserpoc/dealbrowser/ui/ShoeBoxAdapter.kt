package com.target.dealbrowserpoc.dealbrowser.ui

import android.support.v4.app.Fragment
import info.juanmendez.shoeboxes.adapters.ShoeFragmentAdapter
import info.juanmendez.shoeboxes.shoes.ShoeRack


/**
 * Created by Juan Mendez on 6/7/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 *
 *
 * This is a support adapter for this application using ShoeBoxes.
 * Fragments are set to hide when inactive, and show when mActive.
 * Route params are only received once
 */
class ShoeBoxAdapter (private var mFragment: Fragment, private var mShoeRack: ShoeRack) : ShoeFragmentAdapter {
    private val mActive: Boolean = false
    private var mShoeTag: String? = null

    init {

        if (mFragment.getId() != 0) {
            mShoeTag = mFragment.getId().toString()
        } else {
            mShoeTag = mFragment.getTag()
        }
    }

    override fun getTag(): String? {
        return mFragment.tag
    }

    override fun getId(): Int {
        return mFragment.id
    }

    /**
     * we ensure mActive or inactive happens just once,
     * then we let the mFragment status if it's of type FragmentNav
     * also in that case we pass the recent route params
     */
    override fun setActive(active: Boolean?) {

        val fm = mFragment.fragmentManager

        if (fm != null) {

            val ft = fm.beginTransaction()

            if (active!!) {
                ft.show(mFragment)
            } else {
                ft.hide(mFragment)
            }

            ft.commit()

            /*if( mFragment instanceof FragmentNav && mActive != active){
                mActive = active;
                if( active ){
                    ((FragmentNav) mFragment).active( mShoeRack.getRouteParamsOnce(mShoeTag) );
                }else{
                    ((FragmentNav) mFragment).inactive( false );
                }
            }*/
        }
    }

    override fun isActive(): Boolean {
        return mActive
    }

    /**
     * upon parent activity pausing we let fragments know there has been a paused
     */
    override fun onRotation() {
        /*if( mFragment instanceof FragmentNav && mActive){
            mActive = false;
            ((FragmentNav) mFragment).inactive( true );
        }*/
    }

    override fun fromChildVisit() {

    }

    override fun toChildVisit() {

    }
}