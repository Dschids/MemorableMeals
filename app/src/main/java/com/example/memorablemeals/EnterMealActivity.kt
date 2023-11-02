package com.example.memorablemeals

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.memorablemeals.Info.MealInfo

class EnterMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_meal)

        val txtRestaurantName = findViewById<EditText>(R.id.idEditTextRestaurant)
        val txtMealName = findViewById<EditText>(R.id.idEditTextMealName)
        val txtMealCost = findViewById<EditText>(R.id.idEditTextDecimalMealCost)
        val spinnerRating = findViewById<Spinner>(R.id.idSpinnerMealRating)
        val txtComments = findViewById<EditText>(R.id.idEdtTxtComments)
        val btnSaveMeal = findViewById<Button>(R.id.idBtnSaveMeal)
        val btnMainMenu = findViewById<Button>(R.id.idBtnMainMenu)

        btnSaveMeal.setOnClickListener {
            // getting variables from the different views.
            val resName = txtRestaurantName.text.toString()
            val mealName = txtMealName.text.toString()
            val rawMealCost = txtMealCost.text.toString()
            val rating = spinnerRating.selectedItem.toString().toInt()
            val comments = txtComments.text.toString()
            /* check that restaurant name, meal name and meal cost fields aren't empty
                rating will never be empty, and comments can be blank.
             */
            if (TextUtils.isEmpty(resName)){
                txtRestaurantName.error = "Restaurant can't be blank."
                txtRestaurantName.requestFocus()
            }
            else if (TextUtils.isEmpty(mealName)){
                txtMealName.error = "Meal Name can't be blank."
                txtMealName.requestFocus()
            }
            else if (TextUtils.isEmpty(rawMealCost)) {
                txtMealCost.error = "Meal Cost can't be blank."
                txtMealCost.requestFocus()
            }
            /* if all required fields contain something convert rawMealCost to a double and pass all
            the variables into the MealInfo class
             */

            else {
                var mealCost = rawMealCost.toDouble()
                var entry = MealInfo(resName,mealName,mealCost,rating,comments)
                // TODO do something with this class I made, append it to a list and pass it to a different activity?

            }




        }

        btnMainMenu.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}