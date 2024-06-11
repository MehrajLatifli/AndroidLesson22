package com.example.producthub.source.local.room.maping

import com.example.producthub.models.entities.ProductEntity
import com.example.producthub.models.get.product.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        availabilityStatus = this.availabilityStatus,
        brand = this.brand,
        category = this.category,
        description = this.description,
        dimensions = this.dimensions,
        discountPercentage = this.discountPercentage,
        id = this.id,
        images = this.images,
        meta = this.meta,
        minimumOrderQuantity = this.minimumOrderQuantity,
        price = this.price,
        rating = this.rating,
        returnPolicy = this.returnPolicy,
        reviews = this.reviews,
        shippingInformation = this.shippingInformation,
        sku = this.sku,
        stock = this.stock,
        tags = this.tags,
        thumbnail = this.thumbnail,
        title = this.title,
        warrantyInformation = this.warrantyInformation,
        weight = this.weight
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        availabilityStatus = this.availabilityStatus,
        brand = this.brand,
        category = this.category,
        description = this.description,
        dimensions = this.dimensions,
        discountPercentage = this.discountPercentage,
        images = this.images,
        meta = this.meta,
        minimumOrderQuantity = this.minimumOrderQuantity,
        price = this.price,
        rating = this.rating,
        returnPolicy = this.returnPolicy,
        reviews = this.reviews,
        shippingInformation = this.shippingInformation,
        sku = this.sku,
        stock = this.stock,
        tags = this.tags,
        thumbnail = this.thumbnail,
        title = this.title,
        warrantyInformation = this.warrantyInformation,
        weight = this.weight
    )
}
