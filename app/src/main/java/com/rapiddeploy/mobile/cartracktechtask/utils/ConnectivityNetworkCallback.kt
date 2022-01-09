package com.rapiddeploy.mobile.cartracktechtask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import androidx.lifecycle.MutableLiveData

class ConnectivityNetworkCallback constructor(private val context: Context):
    ConnectivityManager.NetworkCallback() {

    val isOnline = MutableLiveData<Boolean>()

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        val connected = networkCapabilities.hasCapability(NET_CAPABILITY_INTERNET)
        setIsOnline(connected)
    }

    override fun onAvailable(network: Network) {
        setIsOnline(true)
    }

    override fun onLost(network: Network) {
        setIsOnline(false)
    }

    private fun setIsOnline(connected: Boolean) {
        if (isOnline.value == null || isOnline.value != connected) {
            isOnline.postValue(connected)
        }
    }

    private fun doIsOnlineCheck() {
        val connected = NetworkUtils.isOnline(context)
        setIsOnline(connected)
    }

    init {
        // Manually check network state because we don't get a callback when device isOffline
        doIsOnlineCheck()
    }
}