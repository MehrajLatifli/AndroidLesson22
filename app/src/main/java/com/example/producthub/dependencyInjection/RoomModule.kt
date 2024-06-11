package com.example.producthub.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.example.producthub.source.local.room.LocalDao
import com.example.producthub.source.local.room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideLocalDatabase(application: Application): ProductDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ProductDatabase::class.java,
            "Product_db"
        ).build()
    }

    @Provides
    fun provideLocalDao(localDatabase: ProductDatabase): LocalDao {
        return localDatabase.createDAO()
    }
}
