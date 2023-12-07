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

        // link to listview and button in the xml, create intent to get extras
        val mealList = findViewById<ListView>(R.id.idLvMeals)
        val btnNewMeal = findViewById<Button>(R.id.btnMlNewMeal)
        val extras = intent.extras

        /* list is an array of class Model, that is used to create the custom listview, meals is array
        of class MealInfo that holds all the information for the meals */
        var list = arrayListOf<Model>()
        var meals = arrayListOf<MealInfo>(MealInfo("Arby's", "The Meats", 9.99, 3, "They have them"),
            MealInfo("McDonalds", "Basket of Fries", 8.99, 0, "A whole basket of sadness"))

        // getting extras if there are any to get
        if (extras != null){
            // initialise all the variables
            var mealName = " "
            var resName = " "
            var rating = 0
            var mealCost = 0.0
            var mealComments = " "
            var position = 0
            var delete = false

            /* set all the variables using the correct get extra
            the strings need to have the .toString or it starts throwing errors
             */
            mealName = extras.getString("mealName").toString()
            resName = extras.getString("resName").toString()
            mealCost = extras.getDouble("mealCost")
            rating = extras.getInt("rating")
            mealComments = extras.getString("mealComments").toString()
            position = extras.getInt("position")
            delete = extras.getBoolean("delete")

            /* checking the position variables to see what we're doing,  999 is the position assigned
            when creating a new meal, so we add it to meals.  If delete is true we remove it from the
            list, otherwise we set the values at position with the variables from get extra
             */
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

        // iterate through meals, and add a model class object to list each time
        for (item in meals){
            list.add(Model(item.mealName, item.mealRating))
        }
        // setting up the custom adapter using list
        mealList.adapter = MyCustomAdapter(this, R.layout.custom_list_view, list)

        /* set on item click listener, uses put extra and pulls info from meals using position of
        item clicked
        */
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

        // new meal button takes you to new meal activity
        btnNewMeal.setOnClickListener {
            val newMeal = Intent(this, EnterMealActivity::class.java)
            startActivity(newMeal)
        }








    }
}