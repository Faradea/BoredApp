package com.example.boredapp.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(ActivityEntity::class), version = BoredAppDB.DATABASE_VERSION)
abstract class BoredAppDB : RoomDatabase() {

    abstract fun activityDAO(): ActivityDAO

    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "BoredAppDB"

        @Volatile
        private var INSTANCE: BoredAppDB? = null

        fun getInstance(context: Context): BoredAppDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BoredAppDB::class.java, DATABASE_NAME
            ).build()
    }
}