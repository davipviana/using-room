package com.davipviana.usingroom.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.davipviana.usingroom.R
import com.davipviana.usingroom.database.DatabaseFactory
import com.davipviana.usingroom.delegates.StudentsDelegate
import com.davipviana.usingroom.entities.Student

/**
 * A simple [Fragment] subclass.
 *
 */
class StudentFormFragment : Fragment() {

    private var student: Student = Student()
    private lateinit var txtName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var delegate: StudentsDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as StudentsDelegate
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle("Cadastro de aluno")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_form, container, false)
        initializeWidgets(view)

        loadStudentInformation()

        return view
    }

    private fun loadStudentInformation() {
        if (arguments != null) {
            student = arguments?.getSerializable("student") as Student

            txtName.setText(student.name)
            txtEmail.setText(student.email)
        }
    }

    private fun initializeWidgets(view: View) {
        txtName = view.findViewById(R.id.student_form_name)
        txtEmail = view.findViewById(R.id.student_form_email)

        val btnAdd = view.findViewById<Button>(R.id.student_form_add);

        btnAdd.setOnClickListener {
            updateStudentInfo()

            studentPersist()

            delegate.backToPreviousScreen()
        }
    }

    private fun studentPersist() {
        val studentDao = DatabaseFactory().getDatabase(context as Context).studentDao()

        if (student.id == 0L) {
            studentDao.insert(student)
        } else {
            studentDao.update(student)
        }
    }

    private fun updateStudentInfo() {
        student.apply {
            name = txtName.text.toString()
            email = txtEmail.text.toString()
        }
    }
}