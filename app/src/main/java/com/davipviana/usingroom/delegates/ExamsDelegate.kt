package com.davipviana.usingroom.delegates

import com.davipviana.usingroom.entities.Exam

interface ExamsDelegate {
    fun setActivityTitle(title: String)

    fun handleAddExamClick()

    fun backToPreviousScreen()

    fun handleExamSelected(exam: Exam)
}