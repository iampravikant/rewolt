package com.wolt.restaurants.ui.ext

import android.view.View
import android.widget.ImageView
import androidx.annotation.DimenRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wolt.restaurants.R

/**
 *  true = View.VISIBLE, false = View.GONE
 * */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun ImageView.loadImageFromUrl(url: String, @DimenRes cornerRadius: Int? = null) {
    val builder = Glide.with(context)
        .load(url)
        .centerCrop()

    cornerRadius?.let {
        val cornerRadiusPx = context.resources.getDimensionPixelSize(it)
        builder.transform(CenterCrop(), RoundedCorners(cornerRadiusPx))
    }

    builder.placeholder(R.drawable.default_venue_image).into(this)
}
