package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.lifecycle.Transformations
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.api.support.WebRequestManager

class Repository(private val webRequestManager: WebRequestManager) : TitlesRepository {
    override val omdbResponse = webRequestManager.omdbResponse
    override val titles = Transformations.map(omdbResponse) {
        it?.titles
    }

    override fun getTitles(searchTitle: String, type: Title.Type) {
        webRequestManager.getTitles(searchTitle, type)
    }
}