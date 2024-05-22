package com.example.advance.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advance.model.activity.Activity
import com.example.advance.model.activity.ActivityRepository
import com.example.advance.model.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ActivityViewModel(
    val activityRepository: ActivityRepository = Graph.activityRepository
): ViewModel() {
    var activityTitleState by mutableStateOf("")
    var activityDescriptionState by mutableStateOf("")

    fun onActivityTitleChanged(newString: String){
        activityTitleState = newString
    }

    fun onActivityDescriptionChanged(newString: String){
        activityDescriptionState = newString
    }

    //Variable exists and is initialized before operations are done upon it.
    lateinit var getAllActivities: Flow<List<Activity>>

    init {
        viewModelScope.launch {
            getAllActivities = activityRepository.getActivities()
        }
    }

    fun addActivity(activity: Activity){
        viewModelScope.launch(Dispatchers.IO) {
            activityRepository.addActivity(activity = activity)
        }
    }

    fun getActivityById(id: Int): Flow<Activity>{
        return activityRepository.getActivityById(id)
    }
    fun updateActivity(activity: Activity){
        viewModelScope.launch(Dispatchers.IO) {
            activityRepository.updateActivity(activity = activity)
        }
    }

    fun deleteActivity(activity: Activity){
        viewModelScope.launch {
            activityRepository.deleteActivity(activity)
        }
    }

    fun doneActivity(activity: Activity){
        viewModelScope.launch {
            activityRepository.doneActivity(activity)
        }
    }

}
