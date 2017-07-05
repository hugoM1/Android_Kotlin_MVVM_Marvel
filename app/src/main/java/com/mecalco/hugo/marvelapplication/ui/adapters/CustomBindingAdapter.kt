package com.mecalco.hugo.marvelapplication.ui.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import com.squareup.picasso.Picasso


/**
 * @author by hugo on 6/5/17.
 */

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, character: Characters.DataBean.ResultsBean) {
    val url = character.thumbnail?.path + "." + character.thumbnail?.extension
    Picasso.with(view.context)
            .load(url)
            .into(view)
}

@BindingAdapter("loadComicImage")
fun loadComicImage(view: ImageView, comic: Comics.DataBean.ResultsBean) {
    val url = comic.thumbnail?.path + "." + comic.thumbnail?.extension
    Picasso.with(view.context)
            .load(url)
            .into(view)
}