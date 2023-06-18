package com.example.movieapp.model.user


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class AuthorDetails(
    @SerializedName("avatar_path")
    @Expose
    var avatarPath: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("rating")
    @Expose
    var rating: Int? = null,
    @SerializedName("username")
    @Expose
    var username: String? = null
)