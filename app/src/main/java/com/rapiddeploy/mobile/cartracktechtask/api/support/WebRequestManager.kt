package com.rapiddeploy.mobile.cartracktechtask.api.support

import androidx.lifecycle.MutableLiveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import javax.inject.Inject


class WebRequestManager @Inject constructor() {

    val isLoading = MutableLiveData<Boolean>()

    val omdbResponse = MutableLiveData<OmdbResponse?>()

//    fun getTitles(searchTitle: String, type: Title.Type, page: Int): OmdbResponse? {
//        val call = ApiService.apiInterface.getTitles(searchTitle, type.value, page)
//        isLoading(true)
//        var omdbResponse: OmdbResponse? = null
//
//        call.enqueue(object : Callback<OmdbResponse> {
//            override fun onResponse(call: Call<OmdbResponse>, response: Response<OmdbResponse>) {
//                omdbResponse = response.body()
//                isLoading(false)
//            }
//
//            override fun onFailure(call: Call<OmdbResponse>, t: Throwable) {
//                // TODO: Determine Error with throwable
//                omdbResponse = OmdbResponse.forError()
//                isLoading(false)
//            }
//        })
//
//        return omdbResponse
//    }

    private fun isLoading(loading: Boolean) {
        isLoading.value = loading
    }
}