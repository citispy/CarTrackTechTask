package com.rapiddeploy.mobile.cartracktechtask.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rapiddeploy.mobile.cartracktechtask.api.model.Title
import com.rapiddeploy.mobile.cartracktechtask.api.support.ApiInterface
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE_INDEX = 1

class TitlePagingSource(private val apiInterface: ApiInterface, private val searchTitle: String, private val type: Title.Type) :
    PagingSource<Int, Title>() {

    override fun getRefreshKey(state: PagingState<Int, Title>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Title> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = apiInterface.getTitles(searchTitle, type.value, page)
            val previousKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
            val titles = response.titles
            val nextKey = if (titles.isEmpty()) null else page + 1
            LoadResult.Page(titles, previousKey, nextKey)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}