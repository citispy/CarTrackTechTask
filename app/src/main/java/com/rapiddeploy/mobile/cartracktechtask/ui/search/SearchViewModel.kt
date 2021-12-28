package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.ViewModel
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: TitlesRepository) : ViewModel() {

    val titles = repository.titles

    fun getTitles() {
        repository.getTitles("good", Title.Type.MOVIE)
    }

    init {

    }
}