package com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.ui.search.TitlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TitlesViewModel @Inject constructor(private val repository: TitlesRepository) : ViewModel() {

    private val isLoading = repository.isLoading
    val titles = repository.titles
    val uiState = MediatorLiveData<UiState>()

    var selectedTitle: Title? = null

    fun getTitles(searchTitle: String, type: Title.Type) {
        repository.getTitles(searchTitle, type)
    }

    init {
        uiState.addSource(isLoading) {
            if (isLoading.value == true) {
                uiState.value = UiState.LOADING
            }
        }

        uiState.addSource(titles) {
            if (titles.value != null) {
                uiState.value = UiState.NOT_LOADING_WITH_TITLES
            } else {
                uiState.value = UiState.NOT_LOADING_WITHOUT_TITLES
            }
        }
    }

    enum class UiState {
        LOADING, NOT_LOADING_WITH_TITLES, NOT_LOADING_WITHOUT_TITLES
    }
}