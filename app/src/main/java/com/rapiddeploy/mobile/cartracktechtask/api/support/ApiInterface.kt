package com.rapiddeploy.mobile.cartracktechtask.api.support

import com.rapiddeploy.mobile.cartracktechtask.api.model.OmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/")
    fun getTitles(@Query("s") searchTitle: String, @Query("type") type: String): Call<OmdbResponse>
}