package com.example.mathapp.Model

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class Test(context: Context) {

    private val questionsList: ArrayList<MathQuestions>
    private val topicsList: ArrayList<Topics>
    private val dbHelper: MathDataBase = MathDataBase(context)
    private var score = 0
    init {
        questionsList = dbHelper.get14Questions()
        topicsList = dbHelper.getAllTopics()
    }
    fun get_qList(): ArrayList<MathQuestions> {

        return questionsList
    }
    fun get_topicList(): ArrayList<Topics> {
        return topicsList
    }
    fun get_RightOption(): String {
        var rightOption = "Not found"
        for (e in questionsList)
            if (e.mRightOption == rightOption) {
                rightOption = e.mRightOption
                break
            }
        return rightOption
    }
}