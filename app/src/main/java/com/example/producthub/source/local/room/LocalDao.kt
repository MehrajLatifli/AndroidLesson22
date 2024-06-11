package com.example.producthub.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.producthub.models.entities.ProductEntity

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addProducts(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>

}