package com.example.producthub.models.get.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("products")
    val products: List<Product>?,
    @SerializedName("skip")
    val skip: Int?,
    @SerializedName("total")
    val total: Int?
): Parcelable