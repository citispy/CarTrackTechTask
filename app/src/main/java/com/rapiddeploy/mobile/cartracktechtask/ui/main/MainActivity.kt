package com.rapiddeploy.mobile.cartracktechtask.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rapiddeploy.mobile.cartracktechtask.R
import com.rapiddeploy.mobile.cartracktechtask.databinding.ActivityMainBinding
import com.rapiddeploy.mobile.cartracktechtask.utils.NetworkStateViewModel
import com.rapiddeploy.mobile.cartracktechtask.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val networkStateViewModel: NetworkStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        networkStateViewModel.isOnline.observe(this) {
            if (it) {
                binding.noInternet.visibility = View.GONE
            } else {
                binding.noInternet.visibility = View.VISIBLE
            }
        }
    }
}