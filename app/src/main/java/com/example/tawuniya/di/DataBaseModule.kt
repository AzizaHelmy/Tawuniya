package com.example.tawuniya.di

import android.content.Context
import com.example.tawuniya.data.source.local.TawuniyaDao
import com.example.tawuniya.data.source.local.TawuniyaDataBase
import com.example.tawuniya.data.source.local.TawuniyaRoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideTawuniyaDatabase(
        @ApplicationContext context: Context
    ): TawuniyaDataBase = TawuniyaRoomClient.create(context)

    @Singleton
    @Provides
    fun provideTawuniyaDao(database: TawuniyaDataBase): TawuniyaDao {
        return database.getTawuniyaDao()
    }
}