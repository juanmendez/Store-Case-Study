package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.models.Deal
import com.target.dealbrowserpoc.dealbrowser.databinding.ListDealItemBinding
import info.juanmendez.shoeboxes.ShoeStorage

/**
 * Created by juan on 3/14/18.
 * Holder uses DataBinding in order to update its view.
 */
class DealItemHolder(var binding:ListDealItemBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.dealObservable = DealObservable()
    }

    fun setDeal(deal: Deal, liveDeal:MutableLiveData<Deal> ){
        binding.dealObservable?.deal = deal

        binding.root.findViewById<ViewGroup>( R.id.dealItemLayout).setOnClickListener({
            liveDeal.value = deal

            //redirect to DealItemFragment
            ShoeStorage.getCurrentRack().request( R.id.deal_item )
        })
    }
}