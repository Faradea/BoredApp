package com.example.boredapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDAO {

    @Query("SELECT * FROM activityentity WHERE isFinished = 1 ORDER BY uid DESC")
    suspend fun getAllFinished(): List<ActivityEntity>

    @Query("SELECT * FROM activityentity WHERE isFinished = 0 ORDER BY uid DESC LIMIT 1")
    suspend fun getLastNotFinished(): ActivityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activity: ActivityEntity)
}