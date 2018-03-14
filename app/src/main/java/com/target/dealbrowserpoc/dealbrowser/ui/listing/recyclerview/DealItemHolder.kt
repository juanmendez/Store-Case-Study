package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.support.v7.widget.RecyclerView
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal
import com.target.dealbrowserpoc.dealbrowser.databinding.ListDealItemBinding

/**
 * Created by juan on 3/14/18.
 */
class DealItemHolder(var binding:ListDealItemBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.dealObservable = DealObservable()
    }

    fun setDeal( deal:Deal ){
        binding.dealObservable?.deal = deal
    }
}