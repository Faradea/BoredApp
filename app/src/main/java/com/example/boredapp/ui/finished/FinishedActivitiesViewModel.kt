package com.example.boredapp.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapp.data.repository.ActivityRepository
import com.example.boredapp.ui.model.Activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FinishedActivitiesViewModel : ViewModel() {

    private val _finishedActivities = MutableLiveData<List<Activity>>()

    val finishedActivities: LiveData<List<Activity>> = _finishedActivities

    private val repository = ActivityRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val activities = repository.getAllFinished()
            _finishedActivities.postValue(activities)
        }
    }
}