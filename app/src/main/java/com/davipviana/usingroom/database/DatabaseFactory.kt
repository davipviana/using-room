package com.davipviana.usingroom.database

import android.arch.persistence.room.Room
import android.content.Context

class DatabaseFactory {
    companion object {
        private const val DATABASE_NAME: String = "AppDB";
    }

    fun getDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }
}