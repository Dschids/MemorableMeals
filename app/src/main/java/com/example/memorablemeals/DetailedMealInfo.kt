package com.example.memorablemeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView

class DetailedMealInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_meal_info)


        val btnViewMeals = findViewById<Button>(R.id.btnDtListView)
        val btnEditMeal = findViewById<Button>(R.id.btnDtEditMeal)
        val extras = intent.extras

        if (extras != null) {
            var mealName = " "
            var resName = " "
            var rating = 0
            var mealCost = 0.0
            var mealComments = " "
            var position = 0

            mealName = extras.getString("mealName").toString()
            resName = extras.getString("resName").toString()
            mealCost = extras.getDouble("mealCost")
            rating = extras.getInt("rating")
            mealComments = extras.getString("mealComments").toString()
            position = extras.getInt("position")

            val txtMealName = findViewById<TextView>(R.id.idDtMealName)
            val txtResName = findViewById<TextView>(R.id.idDtRestaurantName)
            val rbMealRating = findViewById<RatingBar>(R.id.rbDtRating)
            val txtMealCost = findViewById<TextView>(R.id.txtDtMealCost)
            val txtMealComments = findViewById<TextView>(R.id.txtDtComments)

            txtResName.text = resName
            txtMealName.text = mealName
            rbMealRating.rating = rating.toFloat()
            txtMealCost.text = mealCost.toString()
            txtMealComments.text = mealComments

            btnEditMeal.setOnClickListener {
                val editMeal = Intent(this, EditMealInfo::class.java).apply{
                    putExtra("mealName", mealName)
                    putExtra("resName", resName)
                    putExtra("mealCost", mealCost)
                    putExtra("rating", rating)
                    putExtra("mealComments", mealComments)
                    putExtra("position", position)
                }
                startActivity(editMeal)
            }

            btnViewMeals.setOnClickListener {
                val viewMeal = Intent(this, MealsListView::class.java)
                startActivity(viewMeal)
            }


        }


    }
}