package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnStart(view: View) {
        val start = Intent(this, MainActivityTestResults::class.java)
        val stu_Name = findViewById<EditText>(R.id.editTextFirstNAme).text.toString()
        val stu_LastName = findViewById<EditText>(R.id.editTextLastName).text.toString()

        if(stu_Name.isEmpty() || stu_LastName.isEmpty())
            Toast.makeText(this,"Please insert your first and last name", Toast.LENGTH_LONG).show()
        else startActivity(start)
    }
    fun btnTeacher(view: View){
        val teacher = Intent(this, MainActivityTeacherPortal::class.java)
        startActivity(teacher)
    }
}