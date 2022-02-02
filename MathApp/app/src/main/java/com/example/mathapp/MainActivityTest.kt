package com.example.mathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mathapp.Model.MathQuestions
import com.example.mathapp.Model.Test
import com.example.mathapp.Model.Topics

class MainActivityTest : AppCompatActivity() {

    lateinit var mTest: Test
    lateinit var qList: ArrayList<MathQuestions>
    lateinit var tList: ArrayList<Topics>
    lateinit var textViewQCount: TextView
    lateinit var rdGroup: RadioGroup
    lateinit var rb1: RadioButton
    lateinit var rb2: RadioButton
    lateinit var rb3: RadioButton
    lateinit var btnConNext: Button

    private var index = 0
    private var qCounter = 1
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        textViewQCount = findViewById(R.id.textViewQCount)
        rdGroup = findViewById(R.id.RadioGroupSurvey)
        btnConNext = findViewById(R.id.btnNextQ)

        mTest = Test(this)
        qList = mTest.get_qList()//Gets the questions
        tList = mTest.get_topicList()

        findViewById<TextView>(R.id.textViewQCount).text =
            "Questions:" + qCounter++ + "/" + mTest.get_qList().size.toString()

        findViewById<TextView>(R.id.textViewQuestions).text = qList[0].mQuestions //get the question
        findViewById<RadioButton>(R.id.rbAnswer1).text = qList[0].mOption1
        findViewById<RadioButton>(R.id.rbAnswer2).text = qList[0].mOption2
        findViewById<RadioButton>(R.id.rbAnswer3).text = qList[0].mOption3

        /*rdGroup.setOnCheckedChangeListener { group, checkedId ->  scoreTest()}
        scoreTest()*/

    }

    fun nextButton(view: View) {
        val scoreMessage = "You scored " + score
        var results = Intent(this, MainActivityResults::class.java).apply {
            putExtra("scoreMessage", scoreMessage)
        }

        if (index + 1 != mTest.get_qList().size) {
            rdGroup.clearCheck()
            index++
            findViewById<TextView>(R.id.textViewQCount).text =
                "Questions:" + qCounter++ + "/" + mTest.get_qList().size.toString()
            findViewById<TextView>(R.id.textViewQuestions).text =
                qList.get(index).mQuestions //get the question
            findViewById<RadioButton>(R.id.rbAnswer1).text = qList.get(index).mOption1
            findViewById<RadioButton>(R.id.rbAnswer2).text = qList.get(index).mOption2
            findViewById<RadioButton>(R.id.rbAnswer3).text = qList.get(index).mOption3

             /*rdGroup.setOnCheckedChangeListener { group, checkedId ->  scoreTest()}
             scoreTest()
            btnConNext.setText("Confirm")*/
        } else if(index + 1 == mTest.get_qList().size) {
            btnConNext.setText("Finish")
            startActivity(results)
        }

    }

    fun scoreTest(view: View){
        val o1 = findViewById<RadioButton>(R.id.rbAnswer1).text
        val o2 = findViewById<RadioButton>(R.id.rbAnswer2).text
        val o3 = findViewById<RadioButton>(R.id.rbAnswer3).text

        val radioGroup = findViewById<RadioGroup>(R.id.RadioGroupSurvey)

        val rO = mTest.get_qList().get(index).mRightOption

        val trueAnswerId = when {
            o1 == rO -> R.id.rbAnswer1
            o2 == rO -> R.id.rbAnswer2
            o3 == rO -> R.id.rbAnswer3
            else -> throw IllegalStateException()
        }
        if (radioGroup.checkedRadioButtonId == trueAnswerId) {
            score++
        }
        else {
            score
        }
    }
}

