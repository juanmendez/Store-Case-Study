package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal
import com.target.dealbrowserpoc.dealbrowser.databinding.ListDealItemBinding
import info.juanmendez.shoeboxes.ShoeStorage
import info.juanmendez.shoeboxes.shoes.ShoeRack

/**
 * Created by juan on 3/14/18.
 * Holder uses DataBinding in order to update its view.
 */
class DealItemHolder(var binding:ListDealItemBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.dealObservable = DealObservable()

    }

    fun setDeal( deal:Deal ){
        binding.dealObservable?.deal = deal
        binding.dealItemHolder = this

        binding.root.findViewById<TextView>( R.id.deal_item_description).setOnClickListener({
            ShoeStorage.getCurrentRack().request( R.id.deal_item.toString() + "/" + deal._id )
        })
    }
}