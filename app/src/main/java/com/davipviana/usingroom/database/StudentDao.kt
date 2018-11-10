package com.davipviana.usingroom.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.davipviana.usingroom.entities.Student

@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Query("SELECT * FROM Student ORDER BY name")
    fun getAll(): List<Student>
}