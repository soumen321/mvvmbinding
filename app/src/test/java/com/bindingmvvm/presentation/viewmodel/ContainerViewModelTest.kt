package com.bindingmvvm.presentation.viewmodel
import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bindingmvvm.data.remote.IContainerRemoteApi
import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.domain.repository.IContainerRepository
import com.bindingmvvm.domain.usecase.UseCaseGetList
import com.bindingmvvm.utility.MainCoroutineRule
import com.bindingmvvm.utility.Resource
import com.bindingmvvm.utility.getOrAwaitValueTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response


class ContainerViewModelTest{

    private lateinit var viewModel: ContainerViewModel

    /*Every Thing will run on single thread*/
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    /*Required To Test Co-Routines*/
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()



    class FakeTotalSavingRemoteApi : IContainerRemoteApi {

        override suspend fun getPhotos(): Response<List<Container>> {
           return Response.success(getFakeSavingData())
        }

        private fun getFakeSavingData():List<Container> {
            return listOf(
                Container(
                    "Hello",
                    "http://google.com",
                    "http://google.com"
                ),
                Container(
                    "Hello World",
                    "http://google.com",
                    "http://google.com"
                )
            )


        }

    }

    class FakeTotalSavingRepository(private val api: IContainerRemoteApi) : IContainerRepository {

        override suspend fun getPhotos(): Response<List<Container>> = api.getPhotos()

    }

    @Before
    fun setUp(){
        viewModel = ContainerViewModel(UseCaseGetList(
            FakeTotalSavingRepository(FakeTotalSavingRemoteApi())
        ))
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `when-valid-data-is-returned-form-api`() {
        viewModel.callFunn()
        val response = viewModel.userListLiveData.getOrAwaitValueTest()
        assertThat(response.data?.size).isEqualTo(2)
    }


    @Test
    fun `when fetching data Loading state shown before content`(){
        var states = mutableListOf<Resource<List<Container>>>()

        viewModel.userListLiveData.observeForever {
            states.add(it)
        }
        viewModel.callFunn()
        dispatcher.scheduler.advanceUntilIdle()


        assert(states[1] is Resource.Loading)
        assert(states[0] is Resource.Success)
    }

    @After
    fun tearDown(){

    }


}