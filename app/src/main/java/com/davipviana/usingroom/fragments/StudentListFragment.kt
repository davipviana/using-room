package com.davipviana.usingroom.fragments


import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.davipviana.usingroom.R
import com.davipviana.usingroom.database.DatabaseFactory
import com.davipviana.usingroom.delegates.StudentsDelegate
import com.davipviana.usingroom.entities.Student

/**
 * A simple [Fragment] subclass.
 *
 */
class StudentListFragment : Fragment() {

    private lateinit var delegate: StudentsDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as StudentsDelegate
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle("Lista de Alunos")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        initializeWidgets(view)

        return view
    }

    private fun initializeWidgets(view: View) {
        initializeStudentList(view)
        initializeFab(view)
    }

    private fun initializeFab(view: View) {
        val studentListFab = view.findViewById<FloatingActionButton>(R.id.student_list_fab)

        studentListFab.setOnClickListener {
            delegate.handleAddButtonClick()
        }
    }

    private fun initializeStudentList(view: View) {
        val studentList = view.findViewById<ListView>(R.id.student_list)

        val students = DatabaseFactory().getDatabase(context as Context).studentDao().getAll()
        val adapter = ArrayAdapter<Student>(context, android.R.layout.simple_list_item_1, students)
        studentList.adapter = adapter



        studentList.setOnItemClickListener { adapterView, view,  position, id ->
            val student = studentList.getItemAtPosition(position) as Student
            delegate.handleStudentSelected(student)
        }
    }


}

