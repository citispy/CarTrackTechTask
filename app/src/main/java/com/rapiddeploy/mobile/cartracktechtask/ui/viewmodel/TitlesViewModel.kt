package com.rapiddeploy.mobile.cartracktechtask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.ui.search.TitlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TitlesViewModel @Inject constructor(private val repository: TitlesRepository) : ViewModel() {

    private val isLoading = repository.isLoading
    val titles = repository.titles
    val uiState = MediatorLiveData<UiState>()
    val errorMessage: LiveData<String?> = repository.errorMessage
    var firstLoad = true

    var selectedTitle: Title? = null

    fun getTitles(searchTitle: String, type: Title.Type): LiveData<PagingData<Title>> {
        return repository.loadTitles(searchTitle, type).cachedIn(viewModelScope)
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