package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.target.dealbrowserpoc.dealbrowser.api.models.Deal
import com.target.dealbrowserpoc.dealbrowser.databinding.ListDealItemBinding
import com.target.dealbrowserpoc.dealbrowser.ui.listing.DealListFragment

/**
 * Created by juan on 3/14/18.
 * Uses LiveData<List<Deal>> in order to refresh
 */
class DealListAdapter(private val inflater: LayoutInflater, private val view: DealListFragment ): RecyclerView.Adapter<DealItemHolder>(), LifecycleObserver {

   private var mDealList = mutableListOf<Deal>()

   init {
       val liveList = view.getMainViewModel().liveDealList

       liveList.observe( view, Observer {
           it?.let{
               mDealList.clear()
               mDealList.addAll( it )
               notifyDataSetChanged()
           }
       })
   }

   override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DealItemHolder {
        var binding = ListDealItemBinding.inflate( inflater, parent, false )
        return DealItemHolder( binding )
    }

    override fun getItemCount(): Int = mDealList.size

    override fun onBindViewHolder(holder: DealItemHolder?, position: Int) {
        holder?.setDeal( mDealList[position], view.getMainViewModel().liveDealSelected )
    }
}