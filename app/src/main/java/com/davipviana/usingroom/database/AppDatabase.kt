package com.davipviana.usingroom.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.davipviana.usingroom.database.converters.DateConverter
import com.davipviana.usingroom.database.daos.ExamDao
import com.davipviana.usingroom.database.daos.StudentDao
import com.davipviana.usingroom.entities.Exam
import com.davipviana.usingroom.entities.Student

@Database(entities = [Student::class, Exam::class], version = 3)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun examDao(): ExamDao
}