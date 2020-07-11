package com.example.boredapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.boredapp.data.remote.ActivityModel
import com.example.boredapp.data.remote.ActivityRemoteStore
import com.example.boredapp.data.remote.BoredAPI
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel() {

    private val _randomActivityLiveData = MutableLiveData<ActivityModel>()
    private val _progressBarLiveData = MutableLiveData<Boolean>()
    private val _dataVisibilityLiveData = MutableLiveData<Boolean>()

    val randomActivity: LiveData<ActivityModel> = _randomActivityLiveData
    val progressBar: LiveData<Boolean> = _progressBarLiveData
    val dataVisibility: LiveData<Boolean> = _dataVisibilityLiveData

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val store : ActivityRemoteStore = ActivityRemoteStore(BoredAPI.create())

    init {
        getRandomActivity()
    }

    fun getRandomActivity() {
        _progressBarLiveData.postValue(true)
        _dataVisibilityLiveData.postValue(false)
        scope.launch {
            val randomActivity = store.getRandomActivity()
            _progressBarLiveData.postValue(false)
            _dataVisibilityLiveData.postValue(true)
            _randomActivityLiveData.postValue(randomActivity)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

}