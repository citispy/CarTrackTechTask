package com.rapiddeploy.mobile.cartracktechtask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {

    fun isOnline(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = manager.activeNetwork ?: return false
        val networkCapabilities = manager.getNetworkCapabilities(network) ?: return false
        return when {
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> true
            else -> false
        }
    }

}