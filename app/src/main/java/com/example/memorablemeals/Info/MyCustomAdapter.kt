package com.example.memorablemeals.Info

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.memorablemeals.R

class MyCustomAdapter (var ctx: Context,
                       var ourResource: Int,
                       var items: ArrayList<Model>): ArrayAdapter<Model>(ctx, ourResource, items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(ourResource,null)

        // go to layout and get links to rating bar and textview
        val mealName = view.findViewById<TextView>(R.id.idTxtMealName)
        val rating = view.findViewById<RatingBar>(R.id.idRbMealRating)

        // set view contents using list of Models
        mealName.text = items[position].mealName
        rating.rating = items[position].rating.toFloat()


        return view
    }
}