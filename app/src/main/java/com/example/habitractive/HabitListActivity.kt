package com.example.habitractive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.habitractive.databinding.ActivityHabitListBinding

class HabitListActivity : AppCompatActivity() {

    object habitListVariables {
        var iconArr = ArrayList<Int>()
        var habitNameArr = ArrayList<String>()
        var habitCountArr = ArrayList<String>() //This is just the string
        var habitRepCountArr = ArrayList<Int>() //The maximum set habit repetition count
        var habitAddedCountArr = ArrayList<Int>() //This variable will be changed based on user
    }


    //--------------------
    lateinit var newHabitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_habit_list)



        val binding = ActivityHabitListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val myListAdapter = MyListAdapter(this, codename, habitCount, imageId)
        val myListAdapter = MyListAdapter(this, habitListVariables.habitNameArr, habitListVariables.habitCountArr, habitListVariables.iconArr, habitListVariables.habitAddedCountArr)
        binding.listView.adapter = myListAdapter

        binding.listView.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            //action to do if item is clicked --- code here

            val intent = Intent(this, HabitInfoActivity::class.java)


            intent.putExtra("HabitArrPos", position)
            intent.putExtra("ItemPos", habitListVariables.habitNameArr[position])
            intent.putExtra("RepetitionPos", habitListVariables.habitCountArr[position])
            intent.putExtra("RepIntPos", habitListVariables.habitRepCountArr[position]) //The actual inputted number amount

            startActivity(intent)

        }

        binding.newHabitBtn.setOnClickListener {

            val intent = Intent(this, NewHabitActivity::class.java)
            startActivity(intent)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}