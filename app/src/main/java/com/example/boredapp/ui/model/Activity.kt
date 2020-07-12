package com.example.boredapp.ui.model

import com.example.boredapp.data.local.ActivityEntity

data class Activity(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String?,
    val key: String,
    val accessibility: Double
)

fun Activity.toEntity(isFinished: Boolean): ActivityEntity {
    val activityEntity = ActivityEntity()
    activityEntity.title = activity
    activityEntity.type = type
    activityEntity.participants = participants
    activityEntity.price = price
    activityEntity.link = link ?: ""
    activityEntity.key = key
    activityEntity.accessibility = accessibility
    activityEntity.isFinished = isFinished
    activityEntity.finishedAt = if (isFinished) System.currentTimeMillis() else 0

    return activityEntity
}