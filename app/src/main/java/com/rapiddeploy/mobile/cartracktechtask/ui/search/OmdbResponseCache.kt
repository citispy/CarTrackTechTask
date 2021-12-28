package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.utils.KEY_TITLE
import com.rapiddeploy.mobile.cartracktechtask.utils.SharedPrefsUtils
import javax.inject.Inject

class OmdbResponseCache @Inject constructor(private val sharedPrefsUtils: SharedPrefsUtils){

    var response = MutableLiveData<OmdbResponse>()

    fun cacheResponse(response: OmdbResponse?) {
        val gson = Gson()
        val responseString = gson.toJson(response)
        sharedPrefsUtils.savePrefs(KEY_TITLE, responseString)
    }

    private fun getCachedResponse() {
        val gson = Gson()
        val titlesString = sharedPrefsUtils.getPrefs(KEY_TITLE)
        val cachedResponse = gson.fromJson(titlesString, OmdbResponse::class.java)
        if (cachedResponse != null) {
            response.value = cachedResponse
        }
    }

    init {
        getCachedResponse()
    }
}