package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityTeacherPortal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_teacher_portal)
    }
    fun btnTeacher(view: View){
        val teacher = Intent(this, MainActivityTeacherQ::class.java)
        startActivity(teacher)
    }
}