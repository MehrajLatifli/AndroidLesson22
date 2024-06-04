package com.example.androidlesson22.models.get.cart


import com.example.androidlesson22.models.get.product.Product
import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("discountedTotal")
    val discountedTotal: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("products")
    val products: List<Product?>?,
    @SerializedName("total")
    val total: Double?,
    @SerializedName("totalProducts")
    val totalProducts: Int?,
    @SerializedName("totalQuantity")
    val totalQuantity: Int?,
    @SerializedName("userId")
    val userId: Int?
)