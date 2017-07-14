package com.mecalco.hugo.marvelapplication.ui.adapters

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.ComicItemLayoutBinding
import com.mecalco.hugo.marvelapplication.model.Comics
import com.mecalco.hugo.marvelapplication.ui.activities.ComicDetailActivity
import javax.inject.Inject

/**
 * @author by hugo on 6/28/17.
 */

class ComicsAdapter @Inject constructor(val mContext: Context) : RecyclerView.Adapter<ComicsAdapter.ComicsAdapterViewHolder>() {

    var mComics: List<Comics.DataBean.ResultsBean> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mComics.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsAdapterViewHolder {
        val binding = DataBindingUtil.inflate<ComicItemLayoutBinding>(
                LayoutInflater.from(parent.context),
                R.layout.comic_item_layout,
                parent,
                false)
        return ComicsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsAdapterViewHolder, position: Int) {
        val itemComic = mComics[position]
        holder.update(itemComic)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, ComicDetailActivity::class.java)
            intent.putExtra("comicID", itemComic.id)
            intent.putExtra("comicTitle", itemComic.title)
            mContext.startActivity(intent)
        }

    }

    class ComicsAdapterViewHolder(val mBindingComicItemLayoutBinding: ComicItemLayoutBinding) :
            RecyclerView.ViewHolder(mBindingComicItemLayoutBinding.root) {

        fun update(comic: Comics.DataBean.ResultsBean) {
            mBindingComicItemLayoutBinding.comic = comic
            mBindingComicItemLayoutBinding.executePendingBindings()
        }
    }

}