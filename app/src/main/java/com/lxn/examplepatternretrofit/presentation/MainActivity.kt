package com.lxn.examplepatternretrofit.presentation


import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager

import com.lxn.examplepatternretrofit.R
import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.platform.recycleview.ItemHolderListener
import com.lxn.examplepatternretrofit.presentation.adapter.MatchAdapter
import com.lxn.examplepatternretrofit.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class MainActivity(override val layoutId: Int? = R.layout.activity_main) : BaseActivity(),
    ItemHolderListener<MatchAdapter.ActionHolder, Any> {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapterMatch: MatchAdapter


    override fun onViewLoaded() {
        super.onViewLoaded()
        initViewModel(viewModel)
        subscribeObservers()
    }

    override fun onSetupView() {
        adapterMatch = MatchAdapter()
        adapterMatch.listener = this
        rcv_match.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterMatch
        }
    }


    private fun subscribeObservers() {
        viewModel.getMatch()
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Match>> -> {
                    onLoading(false)
                    adapterMatch.addItems(
                        dataState.data as ArrayList<Match>,
                        MatchAdapter.TypeHolder.MATCH_ITEM
                    )
                }
                is DataState.Error -> {
                    onLoading(false)
                }
                is DataState.Loading -> {
                    onLoading(true)
                }
            }
        }
    }

    override fun onLoading(isLoading: Boolean) {
        progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

    override fun onItemHolderClicked(
        actionHolder: MatchAdapter.ActionHolder,
        data: Any?,
        position: Int
    ) {
        when (actionHolder) {
            MatchAdapter.ActionHolder.CLICK_ITEM_MATCH -> {
                Log.e("TAG", "Click click")
            }
        }
    }


}