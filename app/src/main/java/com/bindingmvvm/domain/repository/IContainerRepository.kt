package com.bindingmvvm.domain.repository

import com.google.gson.JsonElement
import retrofit2.Response

interface IContainerRepository {
    suspend fun getPhotos() : Response<JsonElement>
}