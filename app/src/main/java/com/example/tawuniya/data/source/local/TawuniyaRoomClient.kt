package com.example.tawuniya.data.source.local

import android.content.Context
import androidx.room.Room

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

internal object TawuniyaRoomClient {

    const val DATABASE_NAME = "tawuniya_database"

    fun create(context: Context): TawuniyaDataBase {
        return Room.databaseBuilder(
            context,
            TawuniyaDataBase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration(true)
             //.addMigrations(MIGRATION_1_2) // For production migrations
            .build()
    }
}