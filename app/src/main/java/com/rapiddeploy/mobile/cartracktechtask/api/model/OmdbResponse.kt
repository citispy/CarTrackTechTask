package com.rapiddeploy.mobile.cartracktechtask.api.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class OmdbResponse {
    @SerializedName("Search")
    @Expose
    var titles: List<Title> = ArrayList()

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null

    @SerializedName("Response")
    @Expose
    var response: String? = null

    @SerializedName("Error")
    @Expose
    var error: String? = null

    companion object {
        fun forError(): OmdbResponse {
            val omdbResponse = OmdbResponse()
            omdbResponse.error = "Error"
            return omdbResponse
        }
    }
}