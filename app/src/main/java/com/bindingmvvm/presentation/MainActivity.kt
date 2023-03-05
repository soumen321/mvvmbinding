package com.bindingmvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bindingmvvm.R
import com.bindingmvvm.presentation.viewmodel.ContainerViewModel
import com.bindingmvvm.utility.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ContainerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stateObserver()
    }

    private fun stateObserver() {
        viewModel.userListLiveData.observe(this){
            when(it){
                is Resource.Loading -> {
                    Log.e("E->", "Loading")
                   // viewBinding?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.e("E->", "Success")
                   // viewBinding?.progressBar?.visibility = View.GONE
                   // it.data?.let { user -> setDataToAdapter(user) }
                }
                is Resource.Error -> {
                   // viewBinding?.progressBar?.visibility = View.GONE
                    Log.e("E->", "" + getString(it.resId!!))
                    //will show error here
                }
            }
        }
    }
}