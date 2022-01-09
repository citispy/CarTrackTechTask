package com.rapiddeploy.mobile.cartracktechtask.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.api.support.ApiInterface
import com.rapiddeploy.mobile.cartracktechtask.api.support.WebRequestManager

class Repository(private val apiInterface: ApiInterface, private val omdbResponseCache: OmdbResponseCache) : TitlesRepository {

    override val isLoading = MutableLiveData<Boolean>()
    override val apiResponse = MutableLiveData<OmdbResponse?>()
    override val cachedApiResponse: MutableLiveData<OmdbResponse?> = omdbResponseCache.response
    override val errorMessage: LiveData<String?> = Transformations.map(apiResponse) {
        it?.error
    }
    override val titles = MutableLiveData<PagingData<Title>>()


    override fun getTitles(searchTitle: String, type: Title.Type) {
        //webRequestManager.getTitles(searchTitle, type)
    }

    override fun loadTitles(searchTitle: String, type: Title.Type) : LiveData<PagingData<Title>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = {TitlePagingSource(apiInterface, searchTitle, type)}
        ).liveData
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }

//    init {
//        titles.addSource(apiResponse) {
//            titles.value = it?.titles
//            omdbResponseCache.cacheResponse(it)
//            Log.d("Repository", "titles set from api")
//        }
//
//        titles.addSource(cachedApiResponse) {
//            titles.value = it?.titles
//            Log.d("Repository", "titles set from cache")
//        }
//    }
}