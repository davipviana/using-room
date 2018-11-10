package com.davipviana.usingroom.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout

import com.davipviana.usingroom.R
import com.davipviana.usingroom.delegates.ExamsDelegate

class ExamListFragment : Fragment() {

    private var delegate: ExamsDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = activity as ExamsDelegate?
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam_list, container, false)
    }


}
