package com.davipviana.usingroom.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.davipviana.usingroom.R
import com.davipviana.usingroom.database.DatabaseFactory
import com.davipviana.usingroom.database.converters.DateConverter
import com.davipviana.usingroom.delegates.ExamsDelegate
import com.davipviana.usingroom.entities.Exam


class ExamFormFragment : Fragment() {

    companion object {
        const val PROVA = "prova"
    }

    private lateinit var delegate: ExamsDelegate
    private lateinit var txtSubject: EditText
    private lateinit var txtDate: EditText
    private lateinit var btnAdd: Button
    private var exam: Exam = Exam()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as ExamsDelegate
        delegate.setActivityTitle("Adicionar prova")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exam_form, container, false)

        initializeWidgets(view)

        loadExamInformation()

        return view
    }

    private fun initializeWidgets(view: View) {
        txtSubject = view.findViewById(R.id.exam_form_subject)
        btnAdd = view.findViewById(R.id.exam_form_add)
        txtDate = view.findViewById(R.id.exam_form_date)

        btnAdd.setOnClickListener {
            updateExamInfo()

            examPersist()

            delegate.backToPreviousScreen()
        }
    }

    private fun updateExamInfo() {
        exam.apply {
            subject = txtSubject.text.toString()
            examDate = DateConverter().convert(txtDate.text.toString())
        }
    }

    private fun examPersist() {

        val examDao = DatabaseFactory().getDatabase(context as Context).examDao()

        if (exam.id == 0L) {
            examDao.insert(exam)
        } else {
            examDao.update(exam)
        }
    }

    private fun loadExamInformation() {
        if (arguments != null) {
            this.exam = arguments?.get(PROVA) as Exam

            txtDate.setText(DateConverter().convertToString(exam.examDate))
            txtSubject.setText(exam.subject)
        }
    }
}
