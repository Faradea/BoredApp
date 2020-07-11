package com.example.boredapp.data.local

import com.example.boredapp.MainApplication

class ActivityLocalStore() {

    private val activityDAO = MainApplication.db.activityDAO()

    fun getCurrentActivity(): List<ActivityEntity> {
        return activityDAO.getFirstNotFinished()
    }

    fun getAllFinishedActivities(): List<ActivityEntity> {
        return activityDAO.getAllFinished()
    }

    fun saveActivity(activityEntity: ActivityEntity) {
        activityDAO.insert(activityEntity)
    }
}