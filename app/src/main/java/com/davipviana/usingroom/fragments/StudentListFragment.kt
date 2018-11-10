package com.davipviana.usingroom.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davipviana.usingroom.R
import com.davipviana.usingroom.activities.StudentsActivity
import com.davipviana.usingroom.delegates.StudentsDelegate

/**
 * A simple [Fragment] subclass.
 *
 */
class StudentListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        val studentListFab = view.findViewById<FloatingActionButton>(R.id.student_list_fab)

        studentListFab.setOnClickListener {
            val delegate = activity as StudentsDelegate
            delegate.handleFabClick()
        }

        return view
    }


}
