package com.fikrisandi.model.remote.movie


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Trailer(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,
    @SerializedName("key")
    @Expose
    var key: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("official")
    @Expose
    var official: Boolean? = null,
    @SerializedName("published_at")
    @Expose
    var publishedAt: String? = null,
    @SerializedName("site")
    @Expose
    var site: String? = null,
    @SerializedName("size")
    @Expose
    var size: Int? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null
)