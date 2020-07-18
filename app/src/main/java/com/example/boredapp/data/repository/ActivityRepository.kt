package com.example.boredapp.data.repository

import com.example.boredapp.data.local.ActivityLocalStore
import com.example.boredapp.data.local.toDomain
import com.example.boredapp.data.remote.ActivityRemoteStore
import com.example.boredapp.data.remote.BoredAPI
import com.example.boredapp.data.remote.toDomain
import com.example.boredapp.ui.model.Activity
import com.example.boredapp.ui.model.toEntity

class ActivityRepository {

    private val localStore = ActivityLocalStore()
    private val remoteStore : ActivityRemoteStore = ActivityRemoteStore(BoredAPI.create())

    suspend fun getCurrentActivity(): Activity? =
        localStore.getCurrentActivity()?.toDomain()

    suspend fun getRandomActivity(): Activity? {
        val result = remoteStore.getRandomActivity()
        return if (result.isSuccessful) {
            result.body()?.toDomain()
        } else {
            null
        }
    }

    suspend fun saveCurrentActivity(activity: Activity) =
        localStore.saveActivity(activity.toEntity(isFinished = false))

    suspend fun finishCurrentActivity(activity: Activity) =
        localStore.saveActivity(activity.toEntity(isFinished = true))

    suspend fun getAllFinished() =
        localStore.getAllFinishedActivities().map { it.toDomain() }
}