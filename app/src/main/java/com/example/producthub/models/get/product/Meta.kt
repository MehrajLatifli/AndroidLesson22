package com.example.producthub.models.get.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    @SerializedName("barcode")
    val barcode: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("qrCode")
    val qrCode: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
): Parcelable