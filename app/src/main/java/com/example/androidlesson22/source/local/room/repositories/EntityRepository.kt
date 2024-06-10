package com.example.androidlesson22.source.local.room.repositories

import com.example.androidlesson22.models.entities.ProductEntity
import com.example.androidlesson22.source.local.room.LocalDao
import javax.inject.Inject

class EntityRepository @Inject constructor(
    val localDao: LocalDao
){
    fun addProductEntity(productEntity: ProductEntity) = localDao.addProducts(productEntity)

    suspend fun getProductEntity() = localDao.getProducts()
}