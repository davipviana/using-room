package com.davipviana.usingroom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.davipviana.usingroom.R
import com.davipviana.usingroom.delegates.ExamsDelegate
import com.davipviana.usingroom.entities.Exam
import com.davipviana.usingroom.fragments.ExamFormFragment
import com.davipviana.usingroom.fragments.ExamListFragment

class ExamsActivity : ExamsDelegate, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exams)

        showFragment(ExamListFragment(), false)
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.exams_frame, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun setActivityTitle(title: String) {
        this.title = title
    }

    override fun handleAddExamClick() {
        showFragment(ExamFormFragment(), true)
    }

    override fun backToPreviousScreen() {
        onBackPressed()
    }

    override fun handleExamSelected(exam: Exam) {
        val formFragment = ExamFormFragment()

        val arguments = Bundle();
        arguments.putSerializable(ExamFormFragment.PROVA, exam)
        formFragment.arguments = arguments

        showFragment(formFragment, true)
    }
}
