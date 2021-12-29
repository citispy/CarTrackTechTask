package com.rapiddeploy.mobile.cartracktechtask.api.support

import androidx.lifecycle.MutableLiveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class WebRequestManager @Inject constructor() {

    val isLoading = MutableLiveData<Boolean>()

    val omdbResponse = MutableLiveData<OmdbResponse?>()

    fun getTitles(searchTitle: String, type: Title.Type) {
        val call = ApiService.apiInterface.getTitles(searchTitle, type.value)
        isLoading(true)

        call.enqueue(object : Callback<OmdbResponse> {
            override fun onResponse(call: Call<OmdbResponse>, response: Response<OmdbResponse>) {
                omdbResponse.value = response.body()
                isLoading(false)
            }

            override fun onFailure(call: Call<OmdbResponse>, t: Throwable) {
                omdbResponse.value = null
                isLoading(false)
            }
        })
    }

    private fun isLoading(loading: Boolean) {
        isLoading.value = loading
    }
}