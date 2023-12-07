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

        // link buttons, create an intent to get extras
        val btnViewMeals = findViewById<Button>(R.id.btnDtListView)
        val btnEditMeal = findViewById<Button>(R.id.btnDtEditMeal)
        val extras = intent.extras

        // getting extras if there are any to get
        if (extras != null) {
            // initialise all the variables
            var mealName = " "
            var resName = " "
            var rating = 0
            var mealCost = 0.0
            var mealComments = " "
            var position = 0

            /* set all the variables using the correct get extra
            the strings need to have the .toString or it starts throwing errors
             */
            mealName = extras.getString("mealName").toString()
            resName = extras.getString("resName").toString()
            mealCost = extras.getDouble("mealCost")
            rating = extras.getInt("rating")
            mealComments = extras.getString("mealComments").toString()
            position = extras.getInt("position")

            // link all the text boxes in the xml
            val txtMealName = findViewById<TextView>(R.id.idDtMealName)
            val txtResName = findViewById<TextView>(R.id.idDtRestaurantName)
            val rbMealRating = findViewById<RatingBar>(R.id.rbDtRating)
            val txtMealCost = findViewById<TextView>(R.id.txtDtMealCost)
            val txtMealComments = findViewById<TextView>(R.id.txtDtComments)

            // set the text boxes to the variables from get extra
            txtResName.text = resName
            txtMealName.text = mealName
            rbMealRating.rating = rating.toFloat()
            txtMealCost.text = mealCost.toString()
            txtMealComments.text = mealComments

            /* on clicklistener for the editmeal button, passes all the data using put extra, then
            takes you to the edit meal activity
             */
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

            // on click listener for view meals button takes you to the Meal List View activity

            btnViewMeals.setOnClickListener {
                val viewMeal = Intent(this, MealsListView::class.java)
                startActivity(viewMeal)
            }


        }


    }
}