package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title

interface TitlesRepository {
    val isLoading: MutableLiveData<Boolean>
    val apiResponse: MutableLiveData<OmdbResponse?>
    val cachedApiResponse: MutableLiveData<OmdbResponse?>
    val titles: LiveData<PagingData<Title>>
    val errorMessage: LiveData<String?>

    fun getTitles(searchTitle: String, type: Title.Type)
    fun loadTitles(searchTitle: String, type: Title.Type) : LiveData<PagingData<Title>>
}