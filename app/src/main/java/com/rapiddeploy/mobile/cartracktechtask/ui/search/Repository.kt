package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.api.support.WebRequestManager

class Repository(private val webRequestManager: WebRequestManager, private val omdbResponseCache: OmdbResponseCache) : TitlesRepository {

    override val isLoading: MutableLiveData<Boolean> = webRequestManager.isLoading
    override val apiResponse: MutableLiveData<OmdbResponse?> = webRequestManager.omdbResponse
    override val cachedApiResponse: MutableLiveData<OmdbResponse?> = omdbResponseCache.response
    override val titles = MediatorLiveData<List<Title>>()

    override fun getTitles(searchTitle: String, type: Title.Type) {
        webRequestManager.getTitles(searchTitle, type)
    }

    init {
        titles.addSource(apiResponse) {
            titles.value = it?.titles
            omdbResponseCache.cacheResponse(it)
            Log.d("Repository", "titles set from api")
        }

        titles.addSource(cachedApiResponse) {
            titles.value = it?.titles
            Log.d("Repository", "titles set from cache")
        }
    }
}