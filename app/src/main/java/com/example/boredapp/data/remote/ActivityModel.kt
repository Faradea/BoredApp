package com.example.boredapp.data.remote

import com.example.boredapp.ui.model.Activity


//{
//    "activity": "Take a spontaneous road trip with some friends",
//    "type": "social",
//    "participants": 4,
//    "price": 0.2,
//    "link": "",
//    "key": "2085321",
//    "accessibility": 0.3
//}
data class ActivityModel(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String?,
    val key: String,
    val accessibility: Double
)

fun ActivityModel.toDomain() =
    Activity(
        activity = activity,
        type = type,
        participants = participants,
        price = price,
        link = link,
        key = key,
        accessibility = accessibility
    )