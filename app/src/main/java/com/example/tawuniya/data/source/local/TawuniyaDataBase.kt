package com.example.tawuniya.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tawuniya.data.source.local.entity.UserEntity

/**
 * Created by Aziza Helmy on 11/06/2025.
 */

@Database(entities = [UserEntity::class], version = 6)
abstract class TawuniyaDataBase : RoomDatabase() {
    abstract fun getTawuniyaDao(): TawuniyaDao
}