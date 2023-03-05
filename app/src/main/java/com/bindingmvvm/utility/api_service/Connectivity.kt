package com.dogmvvm.utility.api_service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object Connectivity {

    private fun getNetworkInfo(context: Context?): NetworkInfo? {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    @JvmStatic
    fun isConnected(context: Context?): Boolean {
        if (null == context) return false
        val info = getNetworkInfo(context)
        return info != null && info.isConnected
    }

    /*Network Connection Checking*/
    fun hasInternetConnection(context: Context): Boolean {
        return if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            isConnected(context)
        } else {
            var result = false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }
            result
        }
    }


}