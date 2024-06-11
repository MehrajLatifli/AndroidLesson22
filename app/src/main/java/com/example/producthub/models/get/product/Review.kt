package com.example.producthub.models.get.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String?,
    @SerializedName("reviewerName")
    val reviewerName: String?
): Parcelable