package com.target.dealbrowserpoc.dealbrowser.ui.utils

import android.databinding.BindingAdapter
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG



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

@BindingAdapter( "strikeText")
fun strickTextView( tv: TextView, strikeWhenText:String? ){
    if( strikeWhenText != null && strikeWhenText.isNotEmpty() ){
        tv.paintFlags = tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }else{
        tv.paintFlags = tv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter("textCapitalize")
fun textCapitalize( textView:TextView, text:String ){
    textView.text = text.capitalize()
}
