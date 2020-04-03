package com.shafigh.lektion2android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

val EXTRA_MESSAGE = "com.shafigh.lektion2android.MESSAGE"

class MainActivity : AppCompatActivity() {

    lateinit var questionView : TextView
    var firstNr = 0
    var secondNr = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionView = findViewById(R.id.questionText)

        val button = findViewById<Button>(R.id.button)

        randomizeQuestion()

        //Intent
        button.setOnClickListener {
            startAnswerActivity()
        }

    }

    private fun checkAnswer(): Boolean {
        val userAnswer = findViewById<EditText>(R.id.answerText).text.toString()

        val answerInt = userAnswer.toIntOrNull()

        return firstNr + secondNr == answerInt
    }

    private fun startAnswerActivity(): Unit {
        val rightOrWrong = checkAnswer()

        //Intent is almost similar to PerformSegue in Swift
        //Show which activity we are in and which activity are we going to

        //Apply is for implement functionality inside newly declared constructor
        //putExtra is to send extra information to new Activity
        val intent = Intent(this,AnswerActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,rightOrWrong)
        }

        startActivity(intent)
    }
    
    private fun randomizeQuestion(){
        firstNr = (1..10).random()
        secondNr  = (1..10).random()

        questionView.text = "$firstNr + $secondNr = "
    }
}
