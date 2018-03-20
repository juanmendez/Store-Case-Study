package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDealListBinding
import com.target.dealbrowserpoc.dealbrowser.models.Deal
import com.target.dealbrowserpoc.dealbrowser.ui.DealView
import com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview.DealListAdapter
import com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview.ItemDecorator
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel
import org.androidannotations.annotations.*

/**
 * Created by juan on 3/12/18.
 * 0. Ensures to set up its recyclerview to show list of deals
 * 1. Provides functionality to refresh its recyclerView
 * 2. Glue deals between presenter and view
 */
@DataBound
@EFragment(R.layout.fragment_deal_list)
class DealListFragment: Fragment(), DealView {
    @Bean
    lateinit var mPresenter: DealListPresenter

    @BindingObject
    lateinit var binding:FragmentDealListBinding

    @ViewById
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @ViewById
    lateinit var recyclerView: RecyclerView

    private lateinit var mAdapter: DealListAdapter

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    @AfterViews
    fun afterViews(){
        drawRecyclerView()

        //1
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        //2 Updates made to the list are able to update the view
        binding.dealsObservable = DealsObservable()
    }

    fun onDealsListChange( deals:List<Deal>){
        binding.dealsObservable?.deals = deals
    }

    private fun drawRecyclerView() {
        //0
        mAdapter = DealListAdapter(layoutInflater, this)

        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration( ItemDecorator(context))
    }

    override fun active(route: String?){
        mPresenter.active( route )
        mAdapter.active( route )
    }

    override fun inactive(){
        mPresenter.inactive()
        mAdapter.inactive()
    }

    override fun getLifeCycle(): Lifecycle=activity.lifecycle

    override fun getMainViewModel(): MainViewModel {
        return ViewModelProviders.of( activity ).get( MainViewModel::class.java )
    }
}