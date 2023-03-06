package com.bindingmvvm.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bindingmvvm.R
import com.bindingmvvm.databinding.ActivityMainBinding
import com.bindingmvvm.presentation.adapter.ContainerAdapter
import com.bindingmvvm.presentation.viewmodel.ContainerViewModel
import com.bindingmvvm.utility.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ContainerViewModel by viewModels()
    private lateinit var newsAdapter : ContainerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        newsAdapter = ContainerAdapter()

        stateObserver()
        setUpAdapter()
    }

    private fun stateObserver() {
        viewModel.userListLiveData.observe(this){
            when(it){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    Log.e("E->", "MAppppp  ${ it.data!!.size}")

                    it.data?.let { list -> newsAdapter.setData(list) }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("E->", "" + getString(it.resId!!))
                    //will show error here
                }
            }
        }
    }
    private fun setUpAdapter(){
        val llManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerViewItem = binding.rvItem
        recyclerViewItem.apply {
            adapter = newsAdapter
            layoutManager = llManager
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    llManager.orientation
                )
            )
        }
    }
}