package com.mecalco.hugo.marvelapplication.common

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.example.github.ui.common.DataBoundListAdapter
import com.mecalco.hugo.marvelapplication.AppExecutors
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.CharacterItemLayoutBinding
import com.mecalco.hugo.marvelapplication.model.Characters


class HeroesListAdapter(appExecutors: AppExecutors) : DataBoundListAdapter<Characters.DataBean.ResultsBean, CharacterItemLayoutBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Characters.DataBean.ResultsBean>() {
            override fun areItemsTheSame(oldItem: Characters.DataBean.ResultsBean, newItem: Characters.DataBean.ResultsBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Characters.DataBean.ResultsBean, newItem: Characters.DataBean.ResultsBean): Boolean {
                return oldItem.name == newItem.name
            }
        }) {

    override fun createBinding(parent: ViewGroup): CharacterItemLayoutBinding {
        return DataBindingUtil.inflate<CharacterItemLayoutBinding>(LayoutInflater.from(parent.context), R.layout.character_item_layout, parent, false)
    }

    override fun bind(binding: CharacterItemLayoutBinding, item: Characters.DataBean.ResultsBean) {
        binding.character = item
        binding.executePendingBindings()
    }
}




