package com.example.producthub.models.get.category


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("url")
    val url: String?
)