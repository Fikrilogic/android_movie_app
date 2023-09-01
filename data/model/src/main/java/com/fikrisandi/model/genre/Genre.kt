package com.fikrisandi.model.genre


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

data class Genres(
    @SerializedName("genres")
    @Expose
    var item: List<Genre>? = null,
)

@Parcelize
data class Genre(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null

): Parcelable

