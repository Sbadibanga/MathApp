package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf

class MainActivityResults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_results)

        val score = findViewById<TextView>(R.id.textViewScoreMessage).apply {  }


        val message = intent.getStringExtra("scoreMessage")
        val textView = findViewById<TextView>(R.id.textViewScoreMessage).apply {
            text = message
        }
    }
    fun btnFinish(view: View){
        val finish = Intent(this, MainActivityTestResults::class.java)
        startActivity(finish)
    }
}