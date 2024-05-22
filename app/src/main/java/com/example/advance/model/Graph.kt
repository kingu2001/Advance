package com.example.advance.model

import android.content.Context
import androidx.room.Room
import com.example.advance.model.activity.AdvanceDatabase
import com.example.advance.model.activity.ActivityRepository

/*import com.example.advance.model.user.UserDatabase
import com.example.advance.model.user.UserRepository*/

object Graph {
    lateinit var advanceDB: AdvanceDatabase

    val activityRepository by lazy {
        ActivityRepository(actDao = advanceDB.activityDao())
    }

    fun provide(context: Context){
        advanceDB = Room.databaseBuilder(context, AdvanceDatabase::class.java, "advance.db").fallbackToDestructiveMigration().build()
    }
}