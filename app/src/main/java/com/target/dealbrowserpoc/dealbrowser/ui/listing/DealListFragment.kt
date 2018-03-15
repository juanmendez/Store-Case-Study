package com.target.dealbrowserpoc.dealbrowser.ui.listing

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.ui.DealView
import com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview.DealListAdapter
import com.target.dealbrowserpoc.dealbrowser.ui.main.MainViewModel
import org.androidannotations.annotations.*

/**
 * Created by juan on 3/12/18.
 */
@OptionsMenu(R.menu.menu)
@EFragment(R.layout.fragment_deal_list)
class DealListFragment: Fragment(), DealView {
    @Bean
    lateinit var mPresenter: DealListPresenter

    @ViewById
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @ViewById
    lateinit var recyclerView: RecyclerView

    @AfterInject
    fun afterInject(){
        mPresenter.register( this )
    }

    @AfterViews
    fun afterViews(){

        drawRecyclerView()

        //user can refresh through the screen as well as through menuRefresh()
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.refresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun drawRecyclerView() {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = DealListAdapter(layoutInflater, this)
    }

    override fun active(route: String?) = mPresenter.active( route )

    override fun inactive() = mPresenter.inactive()

    override fun getLifeCycle(): Lifecycle=activity.lifecycle

    /*@Click(R.id.buttonNav)
    fun onButtonNav(){
        ShoeStorage.getCurrentRack().request( R.id.deal_item.toString() + "/1" )
    }*/

    override fun getMainViewModel(): MainViewModel {
        return ViewModelProviders.of( activity ).get( MainViewModel::class.java )
    }

    @OptionsItem(R.id.menu_refresh)
    fun onMenuRefresh(){
        mPresenter.refresh()
    }
}