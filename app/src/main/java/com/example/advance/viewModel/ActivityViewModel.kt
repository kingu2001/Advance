package com.example.advance.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {
    var activityTitleState by mutableStateOf("")
    var activityDescriptionState by mutableStateOf("")

    fun onActivityTitleChanged(newString: String){
        activityTitleState = newString
    }

    fun onActivityDescriptionChanged(newString: String){
        activityDescriptionState = newString
    }

}
