package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.api.support.WebRequestManager

class Repository(private val webRequestManager: WebRequestManager, private val omdbResponseCache: OmdbResponseCache) : TitlesRepository {

    override val omdbResponse = webRequestManager.omdbResponse
    private val cachedOmdbResponse = omdbResponseCache.response
    override val titles = MediatorLiveData<List<Title>>()

    override fun getTitles(searchTitle: String, type: Title.Type) {
        webRequestManager.getTitles(searchTitle, type)
    }

    init {
        titles.addSource(omdbResponse) {
            titles.value = it?.titles
            omdbResponseCache.cacheResponse(it)
            Log.d("Repository", "titles set from api")
        }

        titles.addSource(cachedOmdbResponse) {
            titles.value = it.titles
            Log.d("Repository", "titles set from cache")
        }
    }
}