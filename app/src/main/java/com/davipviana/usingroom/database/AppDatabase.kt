package com.davipviana.usingroom.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.davipviana.usingroom.entities.Student

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}