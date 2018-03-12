package com.target.dealbrowserpoc.dealbrowser.ui.utils

import android.databinding.BindingAdapter
import android.view.View

//make view to display or not based on show xml attribute
@BindingAdapter("show")
fun show(view: View, visible:Boolean ){
    view.visibility = if( visible ) View.VISIBLE else View.GONE
}