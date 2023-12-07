package com.example.memorablemeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* simple two button screen, one button takes you to the list view activity and the other
        takes you to the add new meal activity
         */
        val btnNew = findViewById<Button>(R.id.idBtnCreateNew)
        btnNew.setOnClickListener {
            var addNew = Intent(this, EnterMealActivity::class.java)
            startActivity(addNew)
        }

        val btnView = findViewById<Button>(R.id.idBtnViewExisting)
        btnView.setOnClickListener {
            var listView = Intent(this, MealsListView::class.java)
            startActivity(listView)
        }
    }
}