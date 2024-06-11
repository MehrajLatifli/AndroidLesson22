package com.example.producthub.models.get.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dimensions(
    @SerializedName("depth")
    val depth: Double?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("width")
    val width: Double?
): Parcelable