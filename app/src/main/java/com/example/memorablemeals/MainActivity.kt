package com.example.memorablemeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNew = findViewById<Button>(R.id.idBtnCreateNew)
        btnNew.setOnClickListener {
            var intent = Intent(this, EnterMealActivity::class.java)
            startActivity(intent)
        }
    }
}