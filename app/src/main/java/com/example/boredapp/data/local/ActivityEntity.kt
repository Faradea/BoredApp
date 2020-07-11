package com.example.boredapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ActivityEntity {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "type")
    var type: String = ""

    @ColumnInfo(name = "participants")
    var participants: Int = 0

    @ColumnInfo(name = "price")
    var price: Double = 0.0

    @ColumnInfo(name = "link")
    var link: String = ""

    @ColumnInfo(name = "key")
    var key: String = ""

    @ColumnInfo(name = "accessibility")
    var accessibility: Double = 0.0

    @ColumnInfo(name = "isFinished")
    var isFinished: Boolean = false

    @ColumnInfo(name = "finishedAt")
    var finishedAt: Int = 0
}