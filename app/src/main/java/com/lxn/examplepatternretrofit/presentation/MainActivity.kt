package com.lxn.examplepatternretrofit.presentation
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxn.examplepatternretrofit.constant.DataState
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.databinding.ActivityMainBinding
import com.lxn.examplepatternretrofit.presentation.adapter.MatchAdapter
import com.lxn.examplepatternretrofit.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>()
 {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapterMatch: MatchAdapter
    override fun onViewLoaded() {
        super.onViewLoaded()
        subscribeObservers()
    }

    override fun onSetupView() {
//        adapterMatch = MatchAdapter()
//        binding.rcvMatch.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = adapterMatch
//        }
    }


    private fun subscribeObservers() {
//        viewModel.getMatch()
//        viewModel.dataState.observe(this) { dataState ->
//            when (dataState) {
//                is DataState.Success<List<Match>> -> {
//                    onLoading(false)
//                    adapterMatch.addAllList(dataState.data as ArrayList<Match>)
//                }
//                is DataState.Error -> {
//                    onLoading(false)
//                }
//                is DataState.Loading -> {
//                    onLoading(true)
//                }
//            }
//        }
    }

    override fun onLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }


    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)


}