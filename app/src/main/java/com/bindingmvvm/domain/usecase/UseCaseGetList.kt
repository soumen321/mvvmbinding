package com.bindingmvvm.domain.usecase

import com.bindingmvvm.R
import com.bindingmvvm.data.remote.IContainerRemoteApi
import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.domain.repository.IContainerRepository
import com.bindingmvvm.utility.Resource
import org.json.JSONArray
import retrofit2.Response
import javax.inject.Inject


class UseCaseGetList  @Inject constructor(
    private val repository: IContainerRepository
) {

    suspend operator fun invoke() : Resource<List<Container>> {

        return try{
            val response = repository.getPhotos()
            if (response.isSuccessful) {
                Resource.Success(
                    response.body()?: emptyList()
                )
            } else {
                Resource.Error("Oops!Something went wrong", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }

    }
}