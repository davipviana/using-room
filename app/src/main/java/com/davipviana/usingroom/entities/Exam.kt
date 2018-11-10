package com.davipviana.usingroom.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class Exam : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var subject: String = ""

    var examDate: Calendar = Calendar.getInstance()

    var examResultDate: Calendar = Calendar.getInstance()

    override fun toString(): String {
        return subject
    }
}