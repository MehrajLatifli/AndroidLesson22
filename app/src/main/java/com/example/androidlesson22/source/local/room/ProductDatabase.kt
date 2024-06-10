package com.example.androidlesson22.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidlesson22.models.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun createDAO(): LocalDao
}
