package com.example.memorablemeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class EditMealInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_meal_info)


        val btnEdtSave = findViewById<Button>(R.id.btnEdtSave)
        val btnEdtDelete = findViewById<Button>(R.id.btnEdtDelete)

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

            val txtMealName = findViewById<TextView>(R.id.edtTxtEdtMealName)
            val txtResName = findViewById<TextView>(R.id.edtTxtEdtRestaurant)
            val rbMealRating = findViewById<RatingBar>(R.id.rbEdtRating)
            val txtMealCost = findViewById<TextView>(R.id.edtTxtEdtDecimalMealCost)
            val txtMealComments = findViewById<TextView>(R.id.edtTxtEdtComments)

            txtResName.text = resName
            txtMealName.text = mealName
            rbMealRating.rating = rating.toFloat()
            txtMealCost.text = mealCost.toString()
            txtMealComments.text = mealComments

            btnEdtSave.setOnClickListener {
                // getting variables from the different views.
                resName = txtResName.text.toString()
                mealName = txtMealName.text.toString()
                var rawMealCost = txtMealCost.text.toString()
                rating = rbMealRating.rating.toInt()
                mealComments = txtMealComments.text.toString()
                /* check that restaurant name, meal name and meal cost fields aren't empty
                    rating will never be empty, and comments can be blank.
                 */
                if (TextUtils.isEmpty(resName)) {
                    txtResName.error = "Restaurant can't be blank."
                    txtResName.requestFocus()
                } else if (TextUtils.isEmpty(mealName)) {
                    txtMealName.error = "Meal Name can't be blank."
                    txtMealName.requestFocus()
                } else if (TextUtils.isEmpty(rawMealCost)) {
                    txtMealCost.error = "Meal Cost can't be blank."
                    txtMealCost.requestFocus()
                }
                /* if all required fields contain something convert rawMealCost to a double and pass all
                the variables into the MealInfo class
                 */
                else {

                    var swapmealCost = rawMealCost.toDouble()
                    var listView = Intent(this, MealsListView::class.java).apply {
                        putExtra("mealName", mealName)
                        putExtra("resName", resName)
                        putExtra("mealCost", swapmealCost)
                        putExtra("rating", rating)
                        putExtra("mealComments", mealComments)
                        putExtra("position", position)
                    }
                    startActivity(listView)
                }
            }

            btnEdtDelete.setOnClickListener {
                val delete = true
                val edtListView = Intent(this, MealsListView::class.java).apply{
                    putExtra("position", position)
                    putExtra("delete", delete)
                }
                Toast.makeText(this, "Entry Deleted", Toast.LENGTH_LONG).show()
                startActivity(edtListView)
            }
        }
    }
}