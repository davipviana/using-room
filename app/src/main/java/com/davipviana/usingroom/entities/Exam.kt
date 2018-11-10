package com.davipviana.usingroom.entities

import java.util.*

class Exam {
    var id: Long? = null

    var subject: String = ""

    var examDate: Calendar? = null

    override fun toString(): String {
        return subject
    }
}