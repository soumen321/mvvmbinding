package com.bindingmvvm.domain.usecase

import com.bindingmvvm.domain.model.Container
import com.bindingmvvm.domain.repository.IContainerRepository
import com.bindingmvvm.utility.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class UseCaseGetListTest{

    @Mock
    lateinit var photoApi: IContainerRepository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test GetProduct empty list` () = runBlocking{

        Mockito.`when`(photoApi.getPhotos()).thenReturn(
            Response.success(emptyList())
        )

        val sut = UseCaseGetList(photoApi)
        val result = sut()

        assertEquals(true,result is Resource.Success)
        assertEquals(0,result.data!!.size)
    }


    @Test
    fun `test GetProduct expected list` () = runBlocking{

        val productList = mutableListOf<Container>(
            Container("Product1","https://via.placeholder.com/600/c6c822","https://via.placeholder.com/150/976aef"),
            Container("Product2","https://via.placeholder.com/600/c6c822","https://via.placeholder.com/150/976aef"),
            Container("Product3","https://via.placeholder.com/600/c6c822","https://via.placeholder.com/150/976aef")
        )

        Mockito.`when`(photoApi.getPhotos()).thenReturn(
            Response.success(productList)
        )

        val sut = UseCaseGetList(photoApi)
        val result = sut()

        assertEquals(true,result is Resource.Success)
        assertEquals(3,result.data!!.size)
        assertEquals("Product2",result.data!![1].title)
    }


    @Test
    fun `test GetProduct expected error` () = runBlocking{

        Mockito.`when`(photoApi.getPhotos()).thenReturn(
            Response.error(401,"Unauthorized".toResponseBody())
        )

        val sut = UseCaseGetList(photoApi)
        val result = sut()

        assertEquals(true,result is Resource.Error)
        assertEquals("Oops!Something went wrong",result.message)
    }



}


