package com.bindingmvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.domain.usecase.UseCaseGetList
import com.bindingmvvm.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(private val useCaseContainer: UseCaseGetList) : ViewModel() {


    private val userLiveData = MutableLiveData<Resource<List<Container>>>()

    /* Exposed to View*/
    val userListLiveData: LiveData<Resource<List<Container>>> = userLiveData

    init {
        userLiveData.postValue(Resource.Loading())

        viewModelScope.launch {
            userLiveData.postValue(useCaseContainer())
        }
    }

    fun callFunn() {
        userLiveData.postValue(Resource.Loading())
        viewModelScope.launch {

            userLiveData.postValue(useCaseContainer())
        }
    }


}

