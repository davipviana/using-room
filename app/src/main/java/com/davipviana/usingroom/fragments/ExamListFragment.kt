package com.davipviana.usingroom.fragments


import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import com.davipviana.usingroom.R
import com.davipviana.usingroom.database.DatabaseFactory
import com.davipviana.usingroom.delegates.ExamsDelegate
import com.davipviana.usingroom.entities.Exam
import com.davipviana.usingroom.entities.Student

class ExamListFragment : Fragment() {

    private lateinit var delegate: ExamsDelegate
    private lateinit var examListFab: FloatingActionButton
    private lateinit var examList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as ExamsDelegate
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle("Provas realizadas")

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.exam_list_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_exam_list, container, false)

        initializeWidgets(view)
        return view
    }

    private fun initializeWidgets(view: View) {
        initializeFab(view)
        initializeExamList(view)
    }

    private fun initializeExamList(view: View) {
        examList = view.findViewById(R.id.exam_list)

        val examDao = DatabaseFactory().getDatabase(context as Context).examDao()
        val exams = examDao.getAll()
        examList.adapter = ArrayAdapter<Exam>(context, android.R.layout.simple_list_item_1, exams)

        examList.setOnItemClickListener { _, _, position, _ ->
            val exam = examList.getItemAtPosition(position) as Exam
            delegate.handleExamSelected(exam)
        }

        examList.setOnItemLongClickListener { adapterView, _, position, _ ->
            val selectedExam = adapterView.getItemAtPosition(position) as Exam
            val message = "Deseja excluir a prova $selectedExam ?"

            Snackbar.make(examListFab, message, Snackbar.LENGTH_LONG)
                .setAction("Sim") {
                    examDao.delete(selectedExam)
                    (examList.adapter as ArrayAdapter<Exam>).remove(selectedExam)
                }.show()

            true
        }
    }

    private fun initializeFab(view: View) {
        examListFab = view.findViewById(R.id.exam_list_fab)

        examListFab.setOnClickListener {
            delegate.handleAddExamClick()
        }
    }
}
