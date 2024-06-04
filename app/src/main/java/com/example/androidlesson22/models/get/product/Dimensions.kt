package com.example.androidlesson22.models.get.product


import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("depth")
    val depth: Double?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("width")
    val width: Double?
)