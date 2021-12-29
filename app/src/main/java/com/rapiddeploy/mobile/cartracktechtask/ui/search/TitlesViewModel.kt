package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.ViewModel
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TitlesViewModel @Inject constructor(private val repository: TitlesRepository) : ViewModel() {

    val titles = repository.titles

    fun getTitles(searchTitle: String, type: Title.Type) {
        repository.getTitles(searchTitle, type)
    }
}