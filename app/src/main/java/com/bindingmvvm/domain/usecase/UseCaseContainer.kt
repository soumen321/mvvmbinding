package com.bindingmvvm.domain.usecase

import com.bindingmvvm.R
import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.domain.model.ContainerResponseFilter
import com.bindingmvvm.domain.repository.IContainerRepository
import com.bindingmvvm.utility.Resource
import org.json.JSONArray
import javax.inject.Inject


class UseCaseContainer  @Inject constructor(
    private val repository: IContainerRepository
) {

    suspend operator fun invoke() : Resource<List<Container>> {

        try{
            val response = repository.getPhotos()
            if (response.isSuccessful) {
                response.body()?.let {
                    val jsonArray = JSONArray(response.body().toString())
                    val response = ContainerResponseFilter.containerResponse(jsonArray)
                    if(response.isEmpty()){
                        return Resource.Error("", R.string.lbl_something_wrong)
                    }
                    return  Resource.Success(
                        response
                    )
                }
            } else {
                return Resource.Error("", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
            return Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }
        return Resource.Error("", R.string.lbl_something_wrong)

    }
}