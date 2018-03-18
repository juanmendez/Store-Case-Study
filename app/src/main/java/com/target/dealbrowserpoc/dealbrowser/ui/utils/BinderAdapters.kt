package com.target.dealbrowserpoc.dealbrowser.ui.utils

import android.databinding.BindingAdapter
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.target.dealbrowserpoc.dealbrowser.R


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

@BindingAdapter( "strikeOnSale")
fun strikeOnSale(textView: TextView, strikeWhenText:String? ){

    val shouldStrikeThrough = strikeWhenText != null && strikeWhenText.isNotEmpty()

    if( shouldStrikeThrough ){
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }else{
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }

    show( textView, shouldStrikeThrough)
}

@BindingAdapter( "highlightOnSale")
fun highlightOnSale(textView:TextView, salesText:String? ){
    if(salesText != null ){
        textView.setTextColor( textView.resources.getColor( R.color.secondaryColor))
    }else{
        textView.setTextColor( textView.resources.getColor( R.color.primaryTextColor))
    }
}

@BindingAdapter("textCapitalize")
fun textCapitalize( textView:TextView, text:String ){
    textView.text = text.capitalize()
}
