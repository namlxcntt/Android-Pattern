package com.lxn.examplepatternretrofit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.lxn.examplepatternretrofit.R
import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.platform.recycleview.ItemHolderListener
import com.lxn.examplepatternretrofit.presentation.adapter.MatchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemHolderListener<MatchAdapter.ActionHolder, Any> {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterMatch: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterMatch = MatchAdapter()
        adapterMatch.listener = this
        rcv_match.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterMatch
        }
        viewModel.getMatch()
        subscribeObservers()

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Match>> -> {
                    displayProgressBar(false)
                    adapterMatch.addItems(
                        dataState.data as ArrayList<Match>,
                        MatchAdapter.TypeHolder.MATCH_ITEM
                    )
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
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