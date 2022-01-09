package com.rapiddeploy.mobile.cartracktechtask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkStateRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val networkCallback = ConnectivityNetworkCallback(context)

    val isOnline = networkCallback.isOnline

    private lateinit var connectivityManager: ConnectivityManager

    private fun registerConnectivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        }
    }

    init {
        registerConnectivityListener()
    }
}