package com.davipviana.usingroom.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.davipviana.usingroom.entities.Student

@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>
}