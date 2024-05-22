package com.example.advance.model.activity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ActivityDAO{
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun addActivity(activityEntity: Activity)

    @Query("Select * From `activity-table`")
    abstract fun getAllActivities() : Flow<List<Activity>>

    @Query("Select * From `activity-table` Where id=:id")
    abstract fun getActivityById(id : Int) : Flow<Activity>

    @Update
    abstract suspend fun updateActivity(activityEntity: Activity)

    @Delete
    abstract suspend fun deleteActivity(activityEntity: Activity)

    @Update
    abstract suspend fun doneActivity(activityEntity: Activity)

}