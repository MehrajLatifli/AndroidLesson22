package com.example.androidlesson22.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidlesson22.models.entities.ProductEntity

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addProducts(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>

}