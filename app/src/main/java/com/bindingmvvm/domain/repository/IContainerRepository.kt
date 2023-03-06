package com.bindingmvvm.domain.repository
import com.bindingmvvm.domain.model.Container
import com.google.gson.JsonElement
import retrofit2.Response

interface IContainerRepository {
    suspend fun getPhotos() : Response<List<Container>>
}