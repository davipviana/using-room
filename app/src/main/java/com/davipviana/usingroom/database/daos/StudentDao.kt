package com.davipviana.usingroom.database.daos

import android.arch.persistence.room.*
import com.davipviana.usingroom.entities.Student

@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM Student ORDER BY name")
    fun getAll(): List<Student>


}