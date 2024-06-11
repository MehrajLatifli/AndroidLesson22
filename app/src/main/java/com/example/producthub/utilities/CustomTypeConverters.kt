package com.example.producthub.utilities

import androidx.room.TypeConverter
import com.example.producthub.models.get.product.Dimensions
import com.example.producthub.models.get.product.Meta
import com.example.producthub.models.get.product.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CustomTypeConverters {


    private val gson = Gson()

    @TypeConverter
    fun fromDimensions(dimensions: Dimensions?): String? {
        return gson.toJson(dimensions)
    }

    @TypeConverter
    fun toDimensions(dimensionsString: String?): Dimensions? {
        return gson.fromJson(dimensionsString, Dimensions::class.java)
    }

    @TypeConverter
    fun fromImagesList(images: List<String>?): String? {
        return images?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toImagesList(imagesString: String?): List<String>? {
        return imagesString?.split(",")?.map { it.trim() }
    }

    @TypeConverter
    fun fromMeta(meta: Meta?): String? {
        return gson.toJson(meta)
    }

    @TypeConverter
    fun toMeta(metaString: String?): Meta? {
        return gson.fromJson(metaString, Meta::class.java)
    }

    @TypeConverter
    fun fromReviewsList(reviews: List<Review>?): String? {
        return gson.toJson(reviews)
    }

    @TypeConverter
    fun toReviewsList(reviewsString: String?): List<Review>? {
        val listType = object : TypeToken<List<Review>>() {}.type
        return gson.fromJson(reviewsString, listType)
    }
}