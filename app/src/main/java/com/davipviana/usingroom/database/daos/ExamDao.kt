package com.davipviana.usingroom.database.daos

import android.arch.persistence.room.*
import com.davipviana.usingroom.entities.Exam
import java.util.*

@Dao
interface ExamDao {

    @Insert
    fun insert(exam: Exam)

    @Update
    fun update(exam: Exam)

    @Delete
    fun delete(exam: Exam)

    @Query("select * from Exam")
    fun getAll(): List<Exam>

    @Query("select * from Exam where examDate between :start and :end")
    fun getByPeriod(start: Calendar, end: Calendar): List<Exam>
}