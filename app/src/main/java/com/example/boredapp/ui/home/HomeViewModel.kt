package com.example.boredapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapp.data.local.ActivityLocalStore
import com.example.boredapp.data.remote.ActivityModel
import com.example.boredapp.data.remote.ActivityRemoteStore
import com.example.boredapp.data.remote.BoredAPI
import com.example.boredapp.data.repository.ActivityRepository
import com.example.boredapp.ui.model.Activity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel() {

    private val _randomActivityLiveData = MutableLiveData<Activity>()
    private val _progressBarLiveData = MutableLiveData<Boolean>()
    private val _dataVisibilityLiveData = MutableLiveData<Boolean>()

    val randomActivity: LiveData<Activity> = _randomActivityLiveData
    val progressBar: LiveData<Boolean> = _progressBarLiveData
    val dataVisibility: LiveData<Boolean> = _dataVisibilityLiveData

//    private val parentJob = Job()
//
//    private val coroutineContext: CoroutineContext
//        get() = parentJob + Dispatchers.Default
//
//    private val scope = CoroutineScope(coroutineContext)

    private val repository = ActivityRepository()


    init {
        viewModelScope.launch {
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
        viewModelScope.launch {
            val randomActivity = repository.getRandomActivity()
            randomActivity?.let { repository.saveCurrentActivity(it) }
            _progressBarLiveData.postValue(false)
            _dataVisibilityLiveData.postValue(true)
            _randomActivityLiveData.postValue(randomActivity)
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