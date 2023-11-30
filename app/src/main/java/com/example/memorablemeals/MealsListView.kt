package com.example.memorablemeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ListView
import com.example.memorablemeals.Info.MealInfo
import com.example.memorablemeals.Info.Model
import com.example.memorablemeals.Info.MyCustomAdapter

class MealsListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals_list_view)

        val mealList = findViewById<ListView>(R.id.idLvMeals)
        val btnNewMeal = findViewById<Button>(R.id.btnMlNewMeal)
        val extras = intent.extras

        var list = arrayListOf<Model>()
        var meals = arrayListOf<MealInfo>(MealInfo("Arby's", "The Meats", 9.99, 3, "They have them"),
            MealInfo("McDonalds", "Basket of Fries", 8.99, 0, "A whole basket of sadness"))

        if (extras != null){
            var mealName = " "
            var resName = " "
            var rating = 0
            var mealCost = 0.0
            var mealComments = " "
            var position = 0
            var delete = false

            mealName = extras.getString("mealName").toString()
            resName = extras.getString("resName").toString()
            mealCost = extras.getDouble("mealCost")
            rating = extras.getInt("rating")
            mealComments = extras.getString("mealComments").toString()
            position = extras.getInt("position")
            delete = extras.getBoolean("delete")

            if (position == 999){
                meals.add(MealInfo(resName, mealName, mealCost, rating, mealComments))
            }
            else if (delete){
                meals.removeAt(position)
            }
            else{
                meals[position] = MealInfo(resName, mealName, mealCost, rating, mealComments)
            }

        }
        for (item in meals){
            list.add(Model(item.mealName, item.mealRating))
        }

        mealList.adapter = MyCustomAdapter(this, R.layout.custom_list_view, list)

        mealList.setOnItemClickListener { parent, view, position, id ->
            val detailedInfo = Intent(this, DetailedMealInfo::class.java).apply{
                putExtra("mealName", meals[position].mealName)
                putExtra("resName", meals[position].restaurantName)
                putExtra("mealCost", meals[position].mealCost)
                putExtra("rating", meals[position].mealRating)
                putExtra("mealComments", meals[position].mealComments)
                putExtra("position", position)
            }
            startActivity(detailedInfo)
        }

        btnNewMeal.setOnClickListener {
            val newMeal = Intent(this, EnterMealActivity::class.java)
            startActivity(newMeal)
        }








    }
}