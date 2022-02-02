package com.example.mathapp.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


/* Database Config*/
private val DataBaseName = "MathQuestions.db"
private val ver : Int = 1

class MathDataBase(context: Context) : SQLiteOpenHelper(context, DataBaseName, null, ver){


    /*Topics table*/
    public val TopicsTableName ="Topics"
    public val TopicsColumn_ID = "ID"
    public val Column_TopicName = "Topic"

    /*************************/

    /*MathQ table*/
    public val QuestionTableName = "Questions"
    public val QColumn_ID = "ID"
    public val QTopicColumn_ID = "TopicID"
    public val Column_Question = "Question"
    public val Column_Option1 = "Option1"
    public val Column_Option2 = "Option2"
    public val Column_Option3 = "Option3"
    public val Column_OptionR = "RightOption"

    /*************************/

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var sqlCreateStatement: String = "CREATE TABLE " + QuestionTableName + " ( " + QColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Question + " TEXT NOT NULL, " + Column_TopicName + " TEXT NOT NULL, " +
                    Column_Option1 + " TEXT NOT NULL, " + Column_Option2 + " TEXT NOT NULL, " + Column_Option3 + " TEXT NOT NULL, " +
                    Column_OptionR + " TEXT)"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + TopicsTableName + " ( " + TopicsColumn_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_TopicName + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)
        }
        catch(e : SQLException) {  }

    }

    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun get14Questions(): ArrayList<MathQuestions> {

        val qList = ArrayList<MathQuestions>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '1'  ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '2' ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '3' ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '4' ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '5' ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '6' ORDER BY random() LIMIT 2)\n" +
                "UNION SELECT * FROM $QuestionTableName WHERE rowid IN (SELECT rowid FROM $QuestionTableName WHERE $QTopicColumn_ID = '7' ORDER BY random() LIMIT 2)\n" +
                "ORDER BY $QTopicColumn_ID /*the rows sorted according to topic */ ;" +
                ";"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val tId: Int = cursor.getInt(1)
                val question: String = cursor.getString(2)
                val option1: String = cursor.getString(3)
                val option2: String = cursor.getString(4)
                val option3: String = cursor.getString(5)
                val optionR: String = cursor.getString(6)


                val p = MathQuestions(id, tId, question, option1, option2, option3, optionR)
                qList.add(p)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return qList
    }

    fun getAllTopics(): ArrayList<Topics> {

        val topicList = ArrayList<Topics>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TopicsTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val topic: String = cursor.getString(1)
                val t = Topics(id, topic)
                topicList.add(t)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return topicList
    }
    fun addQuestion(question: MathQuestions): Int{

        val isQuestionAlreadyExists = checkQuestion(question)
        if (isQuestionAlreadyExists < 0)
            return isQuestionAlreadyExists

        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()
        cv.put(QTopicColumn_ID, question.topicID)
        cv.put(Column_Question, question.mQuestions)
        cv.put(Column_Option1, question.mOption1)
        cv.put(Column_Option2, question.mOption2)
        cv.put(Column_Option3, question.mOption3)
        cv.put(Column_OptionR, question.mRightOption)

        val success = db.insert(QuestionTableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt()
        else return 1

    }
    private fun checkQuestion(question: MathQuestions): Int{

        val db: SQLiteDatabase
        try{
            db = this.readableDatabase
        }
        catch (e: SQLiteException){
            return -2
        }
        val mQuestion = question.mQuestions.lowercase()

        val sqlStatement = "SELECT * FROM $QuestionTableName WHERE $Column_Question = ?"
        val param = arrayOf(mQuestion)
        val cursor: Cursor = db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            val n = cursor.getInt(0)
            cursor.close()
            return -3
        }

        cursor.close()
        db.close()
        return 0
    }
}
