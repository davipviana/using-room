package com.davipviana.usingroom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.davipviana.usingroom.R
import com.davipviana.usingroom.delegates.StudentsDelegate
import com.davipviana.usingroom.entities.Student
import com.davipviana.usingroom.fragments.StudentFormFragment
import com.davipviana.usingroom.fragments.StudentListFragment
import kotlin.concurrent.fixedRateTimer

class StudentsActivity : StudentsDelegate, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        showFragment(StudentListFragment(), false)
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.students_frame, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun handleAddButtonClick() {
        showFragment(StudentFormFragment(), true)
    }

    override fun backToPreviousScreen() {
        onBackPressed()
    }

    override fun setActivityTitle(title: String) {
        this.title = title
    }

    override fun handleStudentSelected(student: Student) {
        val formFragment = StudentFormFragment()

        val arguments = Bundle();
        arguments.putSerializable("student", student)
        formFragment.arguments = arguments

        showFragment(formFragment, true)
    }
}
