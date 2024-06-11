package com.example.producthub.source.local.room.repositories

import com.example.producthub.models.entities.ProductEntity
import com.example.producthub.source.local.room.LocalDao
import javax.inject.Inject

class EntityRepository @Inject constructor(
    val localDao: LocalDao
){
    fun addProductEntity(productEntity: ProductEntity) = localDao.addProducts(productEntity)

    suspend fun getProductEntity() = localDao.getProducts()
}