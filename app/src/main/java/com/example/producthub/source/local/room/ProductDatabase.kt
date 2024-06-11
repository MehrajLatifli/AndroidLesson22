package com.example.producthub.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.producthub.models.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun createDAO(): LocalDao
}
