package com.target.dealbrowserpoc.dealbrowser.ui.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

//make view to display or not based on show xml attribute
@BindingAdapter("show")
fun show(view: View, visible:Boolean ){
    view.visibility = if( visible ) View.VISIBLE else View.GONE
}


@BindingAdapter("picassoDrawable")
fun picassoImageResource(imageView: ImageView, drawableUri:String?){

    if( drawableUri?.isNotEmpty() == true ){

        imageView.visibility = View.VISIBLE
        val picasso = Picasso.with( imageView.context )
        val context = imageView.context
        val resourceId = context.resources.getIdentifier( drawableUri, "drawable", context.packageName )

        picasso.load(resourceId).fit()
                .centerInside().into(imageView)
    }else{
        imageView.visibility = View.INVISIBLE
    }
}