package com.example.memorablemeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ListView
import com.example.memorablemeals.Info.MealInfo
import com.example.memorablemeals.Info.Model
import com.example.memorablemeals.Info.MyCustomAdapter

class MealsListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals_list_view)

        val mealList = findViewById<ListView>(R.id.idLvMeals)

        var list = arrayListOf<Model>()
        var meals = arrayListOf<MealInfo>(MealInfo("Arby's", "The Meats", 9.99, 3, "They have them"),
            MealInfo("McDonalds", "Basket of Fries", 8.99, 0, "A whole basket of sadness"))

        for (item in meals){
            list.add(Model(item.mealName, item.mealRating))
        }

        mealList.adapter = MyCustomAdapter(this, R.layout.custom_list_view, list)

        val extras = intent.extras

        if (extras != null){
            var mealName = if (TextUtils.isEmpty(extras.getString("mealName"))) {
                " "
            } else {
                extras.getString("mealName").toString()
            }
            val resName = extras.getString("resName")
            val mealCost = extras.getDouble("mealCost")
            val rating = extras.getInt("rating")
            val mealComments = extras.getString("mealComments")
            val position = extras.getInt("position")

            if (position == 999){
                meals.add(MealInfo(resName.toString(), mealName, mealCost, rating,
                    mealComments.toString()
                ))
            } else{
                meals[position] = MealInfo(resName!!, mealName, mealCost, rating, mealComments!!)

            }

        }




    }
}