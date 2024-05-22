package com.example.advance.model.activity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Activity::class],
    version = 2,
    exportSchema = false
)

abstract class AdvanceDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDAO
}