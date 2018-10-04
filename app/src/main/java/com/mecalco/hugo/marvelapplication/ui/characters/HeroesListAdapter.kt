package com.mecalco.hugo.marvelapplication.ui.characters

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.CharacterItemLayoutBinding
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.ui.characterdetail.HeroDetailActivity
import javax.inject.Inject

/**
 * @author by hugo on 6/5/17.
 */

open class HeroesListAdapter @Inject constructor(val mContext: Context) :
        RecyclerView.Adapter<HeroesListAdapter.MainAdapterViewHolder>() {

    var mCharacters: List<Characters.DataBean.ResultsBean> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        val binding = DataBindingUtil.inflate<CharacterItemLayoutBinding>(
                LayoutInflater.from(parent.context),
                R.layout.character_item_layout,
                parent,
                false)
        return MainAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        val itemCharacter = mCharacters[position]
        holder.update(itemCharacter)
        holder.mBindingCharacter.characterImage.setOnClickListener {
            val intent = Intent(mContext, HeroDetailActivity::class.java)
            intent.putExtra("characterID", itemCharacter.id)
            intent.putExtra("characterName", itemCharacter.name)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mCharacters.size
    }

    class MainAdapterViewHolder(val mBindingCharacter: CharacterItemLayoutBinding) :
            RecyclerView.ViewHolder(mBindingCharacter.root) {

        fun update(character: Characters.DataBean.ResultsBean) {
            mBindingCharacter.character = character
            mBindingCharacter.characterName.text = character.name
            mBindingCharacter.executePendingBindings()
        }
    }

}