package com.example.boredapp.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapp.data.local.ActivityLocalStore
import com.example.boredapp.data.remote.ActivityModel
import com.example.boredapp.data.remote.ActivityRemoteStore
import com.example.boredapp.data.remote.BoredAPI
import com.example.boredapp.data.repository.ActivityRepository
import com.example.boredapp.ui.base.SingleLiveEvent
import com.example.boredapp.ui.model.Activity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel() {

    private val _randomActivityLiveData = MutableLiveData<Activity>()
    private val _progressBarLiveData = MutableLiveData<Boolean>()
    private val _dataVisibilityLiveData = MutableLiveData<Boolean>()
    private val _error = SingleLiveEvent<String>()

    val randomActivity: LiveData<Activity> = _randomActivityLiveData
    val progressBar: LiveData<Boolean> = _progressBarLiveData
    val dataVisibility: LiveData<Boolean> = _dataVisibilityLiveData
    val error: LiveData<String> = _error

//    private val parentJob = Job()
//
//    private val coroutineContext: CoroutineContext
//        get() = parentJob + Dispatchers.Default
//
//    private val scope = CoroutineScope(coroutineContext)

    private val repository = ActivityRepository()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarLiveData.postValue(true)
            _dataVisibilityLiveData.postValue(false)
            val currentActivity = repository.getCurrentActivity()
            if (currentActivity != null) {
                _progressBarLiveData.postValue(false)
                _dataVisibilityLiveData.postValue(true)
                _randomActivityLiveData.postValue(currentActivity)
            } else {
                getRandomActivity()
            }
        }
    }

    fun getRandomActivity() {
        _progressBarLiveData.postValue(true)
        _dataVisibilityLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            val randomActivity = repository.getRandomActivity()
            randomActivity?.let {
                repository.saveCurrentActivity(it)
                _dataVisibilityLiveData.postValue(true)
                _randomActivityLiveData.postValue(randomActivity)
            } ?: run {
                _error.postValue("Error getting new activity from server")
                _dataVisibilityLiveData.postValue(true)
            }
            _progressBarLiveData.postValue(false)
        }
    }

    fun setCurrentActivityAsFinished() {
        viewModelScope.launch {
            val currentActivity = repository.getCurrentActivity()
            currentActivity?.let {
                repository.finishCurrentActivity(it)
                getRandomActivity()
            }
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        cancelAllRequests()
//    }
//
//    private fun cancelAllRequests() = coroutineContext.cancel()

}