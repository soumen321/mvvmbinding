package com.dogmvvm.utility.common
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*Common Retrofit Instance to be called
from DI modules from different section*/
object RetrofitContainer {

    /*Retrofit Builder*/
    fun getRetrofitBuilder(apiEndPoint: String): Retrofit.Builder {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(apiEndPoint)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            retrofitBuilder.client(client)
        /*GSON Serialization*/
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder
    }

}