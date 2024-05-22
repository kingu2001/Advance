package com.example.advance.model.activity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity-table")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "Activity-name")
    val title: String = "",
    @ColumnInfo(name = "Activity-description")
    val description: String = "",
    @ColumnInfo(name = "Activity-check")
    val done: Boolean = false
)

object DummyActivity{
    val activityList = listOf(
        Activity(title = "Homework", description = "Read"),
        Activity(title = "Cleaning", description = "Wardrobe"),
        Activity(title = "Exercise", description = "Run")
    )
}