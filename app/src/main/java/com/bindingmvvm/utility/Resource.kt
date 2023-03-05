package com.bindingmvvm.utility

sealed class Resource<T>(
    val data: T? = null,
    val resId: Int? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)


    /*...errorMessage : message from API
    * ... errorMessageResId : message string resources*/
    class Error<T>(
        errorMessage: String,
        errorMessageResId: Int,
        data: T? = null
    ) :
        Resource<T>(data, errorMessageResId, errorMessage)

    class Loading<T> : Resource<T>()

}