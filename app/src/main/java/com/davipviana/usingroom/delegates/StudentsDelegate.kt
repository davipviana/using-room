package com.davipviana.usingroom.delegates

interface StudentsDelegate {
    fun handleAddButtonClick()
    fun backToPreviousScreen()
    fun setActivityTitle(name: String)
}