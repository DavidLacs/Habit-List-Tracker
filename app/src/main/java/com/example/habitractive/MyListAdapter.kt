package com.example.habitractive

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

//should be arraylist
class MyListAdapter(private val context: Activity, private val title: ArrayList<String>,
                    private val habitCountArr: ArrayList<String>, private val imgid: ArrayList<Int>, private val addedRepCount: ArrayList<Int>) : ArrayAdapter<String>(context, R.layout.activity_listview, title) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_listview, null, true)


        val titleText = rowView.findViewById<TextView>(R.id.titleText)
        val habitCount = rowView.findViewById<TextView>(R.id.habit_count)
        val imageView = rowView.findViewById<ImageView>(R.id.iconImageView)

        titleText.text = title[position]
        habitCount.text = addedRepCount[position].toString() + " | " + habitCountArr[position]
        imageView.setImageResource(imgid[position])


        return rowView
    }
}