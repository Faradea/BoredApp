package com.example.boredapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDAO {

    @Query("SELECT * FROM activityentity WHERE isFinished = 1 ORDER BY finishedAt DESC")
    fun getAllFinished(): List<ActivityEntity>

    @Query("SELECT * FROM activityentity WHERE isFinished = 0 ORDER BY finishedAt DESC LIMIT 1")
    fun getFirstNotFinished(): List<ActivityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activity: ActivityEntity)
}