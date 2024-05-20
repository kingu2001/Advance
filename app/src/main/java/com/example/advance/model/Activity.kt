package com.example.advance.model

data class Activity(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
)

object DummyActivity{
    val activityList = listOf(
        Activity(name = "Homework", description = "Read"),
        Activity(name = "Cleaning", description = "Wardrobe"),
        Activity(name = "Exercise", description = "Run")
    )
}