package com.example.boredapp.data.local

import com.example.boredapp.MainApplication

class ActivityLocalStore {

    private val activityDAO = MainApplication.db.activityDAO()

    suspend fun getCurrentActivity(): ActivityEntity? {
        return activityDAO.getLastNotFinished()
    }

    suspend fun getAllFinishedActivities(): List<ActivityEntity> {
        return activityDAO.getAllFinished()
    }

    suspend fun saveActivity(activityEntity: ActivityEntity) {
        activityDAO.insert(activityEntity)
    }
}