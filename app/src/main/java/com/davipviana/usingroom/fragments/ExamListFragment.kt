package com.davipviana.usingroom.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.*

import com.davipviana.usingroom.R
import com.davipviana.usingroom.delegates.ExamsDelegate

class ExamListFragment : Fragment() {

    private lateinit var delegate: ExamsDelegate
    private lateinit var examListFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as ExamsDelegate
        setHasOptionsMenu(true)
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
        initializeFab(view)

        return view
    }


    private fun initializeFab(view: View) {
        examListFab = view.findViewById(R.id.exam_list_fab)

        examListFab.setOnClickListener {
            delegate.handleAddExamClick()
        }
    }
}
