package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivityTestResults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test_results)
    }
    fun btnTakeTest(view: View){
        val takeTest = Intent(this, MainActivityTest::class.java)
        startActivity(takeTest)
    }
    fun btnSeeResults(view: View){
        val SeeResults = Intent(this, MainActivityResults::class.java)
        startActivity(SeeResults)
    }
    fun btnLogOut(view: View){
        val Logout = Intent(this, MainActivity::class.java)
        startActivity(Logout)
    }
}