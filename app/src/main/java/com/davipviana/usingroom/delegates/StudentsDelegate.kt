package com.davipviana.usingroom.delegates

import com.davipviana.usingroom.entities.Student

interface StudentsDelegate {
    fun handleAddButtonClick()
    fun backToPreviousScreen()
    fun setActivityTitle(title: String)
    fun handleStudentSelected(student: Student)
}