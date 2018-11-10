package com.davipviana.usingroom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.davipviana.usingroom.R
import com.davipviana.usingroom.fragments.StudentListFragment

class StudentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        val fragmentManager = supportFragmentManager

        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.students_frame, StudentListFragment())

        transaction.commit()
    }
}
