package com.bindingmvvm.data.remote
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET

interface IContainerRemoteApi {

    @GET("/photos")
    suspend fun getPhotos() : Response<JsonElement>
}