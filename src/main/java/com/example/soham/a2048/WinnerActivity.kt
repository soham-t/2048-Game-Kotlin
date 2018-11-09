package com.example.soham.a2048

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class WinnerActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)
        var i:Intent = intent
        var s:String = i.extras.getString("Score")
        var t:TextView = findViewById(R.id.scorewinner)
        t.setText("Score: $s")
        var t1:TextView = findViewById(R.id.highscorewinner)
        var settings:SharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        var highScore:Int = settings.getInt("HIGH_SCORE",0)
        if(s.toInt()>highScore){
            t1.setText("HighScore: $s")
            var editor:SharedPreferences.Editor = settings.edit()
            editor.putInt("HIGH_SCORE",s.toInt())
            editor.commit()
        }
        else{
            val str = highScore.toString()
            t1.setText("HighScore: $str")
        }
    }
    fun newgame(view:View){
        var i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}