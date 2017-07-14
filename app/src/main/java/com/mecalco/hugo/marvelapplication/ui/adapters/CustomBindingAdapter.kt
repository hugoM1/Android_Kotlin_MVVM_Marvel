package com.mecalco.hugo.marvelapplication.ui.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * @author by hugo on 6/5/17.
 */

@BindingAdapter("imageUrl", "imageExtension")
fun setImage(view: ImageView, imageUrl: String?, extension: String?) {
    val url = imageUrl + "." + extension
    Picasso.with(view.context)
            .load(url)
            .into(view)
}