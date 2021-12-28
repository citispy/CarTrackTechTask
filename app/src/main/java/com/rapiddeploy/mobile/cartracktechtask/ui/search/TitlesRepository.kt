package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title

interface TitlesRepository {
    val omdbResponse: MutableLiveData<OmdbResponse?>
    val titles: LiveData<List<Title>?>

    fun getTitles(searchTitle: String, type: Title.Type)
}