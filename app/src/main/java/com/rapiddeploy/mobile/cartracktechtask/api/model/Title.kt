package com.rapiddeploy.mobile.cartracktechtask.api.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Title {
    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("Year")
    @Expose
    var year: String? = null

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null

    @SerializedName("Poster")
    @Expose
    var poster: String? = null

    enum class Type(val value: String) {
        MOVIE("movie"),
        SERIES("series")
    }
}