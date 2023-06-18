package com.example.movieapp.model.user


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class UserReview(
    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("author_details")
    @Expose
    var authorDetails: AuthorDetails? = null,
    @SerializedName("content")
    @Expose
    var content: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
)