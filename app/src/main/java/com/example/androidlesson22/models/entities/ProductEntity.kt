package com.example.androidlesson22.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidlesson22.models.get.product.Dimensions
import com.example.androidlesson22.models.get.product.Meta
import com.example.androidlesson22.models.get.product.Review
import com.example.androidlesson22.utilities.CustomTypeConverters


@Entity("Products")
@TypeConverters(CustomTypeConverters::class)
data class ProductEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("word_id")
    val id: Int?,
    val availabilityStatus: String?,
    val brand: String?,
    val category: String?,
    val description: String?,
    val dimensions: Dimensions?,
    val discountPercentage: Double?,
    val images: List<String?>?,
    val meta: Meta?,
    val minimumOrderQuantity: Int?,
    val price: Double?,
    val rating: Double?,
    val returnPolicy: String?,
    val reviews: List<Review?>?,
    val shippingInformation: String?,
    val sku: String?,
    val stock: Int?,
    val tags: List<String?>?,
    val thumbnail: String?,
    val title: String?,
    val warrantyInformation: String?,
    val weight: Int?)