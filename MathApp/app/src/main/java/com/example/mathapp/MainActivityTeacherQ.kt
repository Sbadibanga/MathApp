package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mathapp.Model.MathDataBase
import com.example.mathapp.Model.MathQuestions

class MainActivityTeacherQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_teacher_q)
    }
    fun addQuestion(view: View) {
        val mTopics           = findViewById<EditText>(R.id.editTextTopics).text.toString()
        val mQuestion = findViewById<EditText>(R.id.editTextQuestion).text.toString()
        val mOption1 = findViewById<EditText>(R.id.editTextOption1).text.toString()

        val mOption2 = findViewById<EditText>(R.id.editTextOption2).text.toString()
        val mOption3 = findViewById<EditText>(R.id.editTextOption2).text.toString()
        val mOptionR = findViewById<EditText>(R.id.editTextRight).text.toString()
        val message = findViewById<TextView>(R.id.tvMessage)

        val topicNum = if(mTopics.isEmpty()) 0 else mTopics.toInt()

        if (mQuestion.isEmpty() || mOption1.isEmpty() || mOption2.isEmpty() || mOption3.isEmpty() || mOptionR.isEmpty() )
            message.text = "Fill in Question Requirements"
        else{
            val newQ = MathQuestions(-1, topicNum, mQuestion, mOption1, mOption2, mOption3, mOptionR)
            val mydatabase = MathDataBase(this)
            val result = mydatabase.addQuestion(newQ)
            when(result){
                1 -> {
                    message.text = "Question is added succesfully"
                    findViewById<Button>(R.id. btnTeacherAdd).isEnabled = false
                }
                -1 -> message.text = "Error inserting Question"
                -2 -> message.text = "Error can not open database"
                -3 -> message.text = "Question alreay exits"

            }
        }
    }
    fun btnLogOut(view: View){
        val Logout = Intent(this, MainActivity::class.java)
        startActivity(Logout)
    }
}