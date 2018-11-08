package com.mecalco.hugo.marvelapplication.ui.characters

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.base.BaseActivity
import com.mecalco.hugo.marvelapplication.base.VMActivity
import com.mecalco.hugo.marvelapplication.common.HeroesListAdapter
import com.mecalco.hugo.marvelapplication.databinding.CharacterItemLayoutBinding
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.ui.characterdetail.HeroDetailActivity
import com.mecalco.hugo.marvelapplication.ui.characterdetail.HeroDetailActivity.Companion.CHARACTER_NAME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


@VMActivity(layoutId = R.layout.activity_main)
class HeroesListActivity : BaseActivity<HeroesListActivityViewModel>(HeroesListActivityViewModel::class) {


    val onClickListener = View.OnClickListener { view ->
        sort_by_date.setTextColor(ContextCompat.getColor(view!!.context, android.R.color.white))
        sort_by_name.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
        when (view.id) {
            R.id.sort_by_date -> {
                sort_by_date.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_light))
                viewModel.getHerosByDate()
            }
            R.id.sort_by_name -> {
                sort_by_name.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_light))
                viewModel.getHeroesByName()
            }
        }
    }

    private val mHeroesListAdapter: HeroesListAdapter by lazy {
        HeroesListAdapter(viewModel.executors(), this::launchItem)
    }


    override fun setupViews(view: View) {
        setSupportActionBar(view.toolbar)
        view.photosList.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.items_per_row))
        view.photosList.adapter = mHeroesListAdapter
        empty.visibility = View.VISIBLE
        sort_by_date.setOnClickListener(onClickListener)
        sort_by_name.setOnClickListener(onClickListener)
        viewModel.getHeroes()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.add_item -> viewModel.addItem()
            R.id.delet_item -> viewModel.deletItem()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun subscribeToViewModel(viewModel: HeroesListActivityViewModel) {

        viewModel.heroList.observe(this, Observer { characters ->
            empty.visibility = View.INVISIBLE
            mHeroesListAdapter.submitList(characters)

        })
        viewModel.sortByDate.observe(this, Observer {
            sort_by_date.setTextColor(ContextCompat.getColor(sort_by_date.context, android.R.color.holo_red_light))

        })

        viewModel.error.observe(this, Observer { throwable ->
            Log.e(TAG, throwable?.message)
        })

    }

    private fun launchItem(binding: CharacterItemLayoutBinding, item: Characters.DataBean.ResultsBean) {
        if (item.id == null || item.id == 0) {
            Toast.makeText(this, "Item does not have details", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, HeroDetailActivity::class.java)
        intent.putExtra(HeroDetailActivity.CHARACTER_ID, item.id)
        intent.putExtra(CHARACTER_NAME, item.name)
        startActivity(intent)
    }


    companion object {
        const val TAG = "HeroesListActivity"
    }

}
