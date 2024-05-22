package com.example.advance.model.activity

import kotlinx.coroutines.flow.Flow

class ActivityRepository(val actDao : ActivityDAO) {
    suspend fun addActivity(activity: Activity){
        actDao.addActivity(activity)
    }

    fun getActivities(): Flow<List<Activity>> = actDao.getAllActivities()
    fun getActivityById(id: Int): Flow<Activity>{
        return actDao.getActivityById(id)
    }

    suspend fun updateActivity(activity: Activity){
        actDao.updateActivity(activity)
    }

    suspend fun deleteActivity(activity: Activity){
        actDao.deleteActivity(activity)
    }

    suspend fun doneActivity(activity: Activity){
        actDao.doneActivity(activity)
    }
}