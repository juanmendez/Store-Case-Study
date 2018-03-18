package com.target.dealbrowserpoc.dealbrowser.ui.listing.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import com.target.dealbrowserpoc.dealbrowser.R

/**
 * Created by Juan Mendez on 11/18/2017.
 * www.juanmendez.info
 * contact@juanmendez.info
 * credits: https://stackoverflow.com/a/31243174/2184088
 */
class ItemDecorator(context: Context) : ItemDecoration() {
    private val mDivider: Drawable = ContextCompat.getDrawable(context, R.drawable.list_view_divider)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: State?) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount-1) {

            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(canvas)
        }
    }
}