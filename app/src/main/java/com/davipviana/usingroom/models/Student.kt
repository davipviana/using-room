package com.davipviana.usingroom.models

class Student {

    var id: Long? = null
    var name: String = ""
    var email: String? = null


    override fun toString(): String {
        return name
    }
}