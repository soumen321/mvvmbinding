package com.bindingmvvm.di
import com.bindingmvvm.data.remote.IContainerRemoteApi
import com.bindingmvvm.data.repository.ContainerRepositoryImpl
import com.bindingmvvm.domain.repository.IContainerRepository
import com.bindingmvvm.domain.usecase.UseCaseContainer
import com.bindingmvvm.utility.api_service.ServiceUrl.BASEURL
import com.dogmvvm.utility.common.RetrofitContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRemoteContainerApi() : IContainerRemoteApi =
        RetrofitContainer.getRetrofitBuilder(BASEURL)
            .build().create(IContainerRemoteApi::class.java)

    @Singleton
    @Provides
    fun providesContainerRepository(
        api: IContainerRemoteApi
    ) : IContainerRepository =
        ContainerRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesGetContainerUseCase(
        repo: IContainerRepository) = UseCaseContainer(repo)

}