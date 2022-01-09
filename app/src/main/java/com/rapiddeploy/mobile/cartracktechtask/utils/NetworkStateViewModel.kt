package com.rapiddeploy.mobile.cartracktechtask.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkStateViewModel @Inject constructor(networkStateRepository: NetworkStateRepository): ViewModel() {
    val isOnline: LiveData<Boolean> = networkStateRepository.isOnline
}