package com.rapiddeploy.mobile.cartracktechtask.api.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class OmdbResponse {
    @SerializedName("Search")
    @Expose
    var titles: List<Title>? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null

    @SerializedName("Response")
    @Expose
    var response: String? = null
}