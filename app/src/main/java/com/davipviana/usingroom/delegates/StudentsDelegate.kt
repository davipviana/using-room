package com.davipviana.usingroom.delegates

import com.davipviana.usingroom.entities.Student

interface StudentsDelegate {
    fun handleAddButtonClick()
    fun backToPreviousScreen()
    fun setActivityTitle(name: String)
    fun handleStudentSelected(student: Student)
}