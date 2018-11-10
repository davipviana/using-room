package com.davipviana.usingroom.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.davipviana.usingroom.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dash_btn_aluno.setOnClickListener {
            startActivity(Intent(this, StudentsActivity::class.java))

        }
    }
}
