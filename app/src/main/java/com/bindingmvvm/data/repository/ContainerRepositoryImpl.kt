package com.bindingmvvm.data.repository
import com.bindingmvvm.data.remote.IContainerRemoteApi
import com.bindingmvvm.domain.repository.IContainerRepository

class ContainerRepositoryImpl(
    private val api: IContainerRemoteApi
) : IContainerRepository {
    override suspend fun getPhotos() = api.getPhotos()
}