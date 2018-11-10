package com.davipviana.usingroom.fragments


import android.arch.persistence.room.Database
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.*
import android.widget.*

import com.davipviana.usingroom.R
import com.davipviana.usingroom.database.DatabaseFactory
import com.davipviana.usingroom.database.converters.DateConverter
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item!!.itemId == R.id.menu_exam_list_calendar) {
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.VERTICAL

            val txtStart = EditText(context)
            txtStart.hint = "Inicio"
            txtStart.inputType = InputType.TYPE_DATETIME_VARIATION_DATE

            val txtEnd = EditText(context)
            txtEnd.hint = "Fim"
            txtEnd.inputType = InputType.TYPE_DATETIME_VARIATION_DATE

            linearLayout.addView(txtStart)
            linearLayout.addView(txtEnd)


            AlertDialog.Builder(context!!)
                .setView(linearLayout)
                .setMessage("Digite as datas para busca")
                .setPositiveButton("Buscar") { _, _ ->
                    val startDate = DateConverter().convert(txtStart.text.toString())
                    val endDate = DateConverter().convert(txtEnd.text.toString())

                    val examDao = DatabaseFactory()
                                    .getDatabase(context as Context)
                                    .examDao()


                    val exams = examDao.getByPeriod(startDate, endDate)

                    examList.adapter = ArrayAdapter<Exam>(context, android.R.layout.simple_list_item_1, exams)
                }
                .setNegativeButton("Cancelar", null)
                .show()

        }

        return true
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
