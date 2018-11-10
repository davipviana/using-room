package com.davipviana.usingroom.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Student : Serializable {
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "email")
    var email: String? = null
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String {
        return name
    }
}