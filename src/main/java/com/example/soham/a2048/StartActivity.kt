package com.example.soham.a2048

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class StartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
    fun startGame(view:View){
        var i:Intent = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}