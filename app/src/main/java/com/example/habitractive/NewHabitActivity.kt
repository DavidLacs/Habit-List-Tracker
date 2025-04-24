package com.example.habitractive

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.habitractive.HabitListActivity.habitListVariables
import com.example.habitractive.databinding.ActivityHabitListBinding
import com.example.habitractive.databinding.ActivityNewHabitBinding

class NewHabitActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var habitNameInput : EditText
    lateinit var repetitionsInput : EditText

    lateinit var exerciseBtn : ImageButton
    lateinit var vegetableBtn : ImageButton
    lateinit var waterBtn : ImageButton
    lateinit var bookBtn : ImageButton
    lateinit var meditateBtn : ImageButton
    lateinit var writeBtn : ImageButton
    lateinit var cookBtn : ImageButton
    lateinit var cleanBtn : ImageButton

    lateinit var iconImage : ImageView

    lateinit var submitBtn : Button
    lateinit var cancelBtn : Button

    var choseIcon : String = "none"
    var chosenIconInt : Int = 0

    var habitNameText : String = "none"
    var numberOfRep : Int = 0
    var numberOfRepString : String = "0 | 0"

    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_habit)

        databaseHelper = DatabaseHelper(this)

        habitNameInput = findViewById(R.id.habitName_input)
        repetitionsInput = findViewById(R.id.repetitions_input)

        exerciseBtn = findViewById(R.id.exercise_btn)
        vegetableBtn = findViewById(R.id.vegetable_btn)
        waterBtn = findViewById(R.id.water_btn)
        bookBtn = findViewById(R.id.book_btn)
        meditateBtn = findViewById(R.id.meditate_btn)
        writeBtn = findViewById(R.id.write_btn)
        cookBtn = findViewById(R.id.cook_btn)
        cleanBtn = findViewById(R.id.cleaning_btn)

        submitBtn = findViewById(R.id.submit_btn)
        cancelBtn = findViewById(R.id.cancel_btn)

        iconImage = findViewById(R.id.iconImageView)

        exerciseBtn.setOnClickListener(this)
        vegetableBtn.setOnClickListener(this)
        waterBtn.setOnClickListener(this)
        bookBtn.setOnClickListener(this)
        meditateBtn.setOnClickListener(this)
        writeBtn.setOnClickListener(this)
        cookBtn.setOnClickListener(this)
        cleanBtn.setOnClickListener(this)

        submitBtn.setOnClickListener(this)
        cancelBtn.setOnClickListener(this)

        cancelBtn.setOnClickListener {

            val intent = Intent(this, HabitListActivity::class.java)
            startActivity(intent)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {

        submitBtn.setOnClickListener {

            habitNameText = habitNameInput.text.toString()
            numberOfRep = repetitionsInput.text.toString().toInt()

            numberOfRepString = "$numberOfRep" //String for the max number of repetitions

            //Add inputs to the array list in the class HabitListActivity
            HabitListActivity.habitListVariables.habitNameArr.add(habitNameText)
            HabitListActivity.habitListVariables.habitCountArr.add(numberOfRepString)
            HabitListActivity.habitListVariables.iconArr.add(chosenIconInt)
            HabitListActivity.habitListVariables.habitRepCountArr.add(numberOfRep)
            HabitListActivity.habitListVariables.habitAddedCountArr.add(0)

            //Insert variables to database
            //databaseHelper.insertHabit(chosenIconInt, habitNameText, numberOfRepString, numberOfRep, 0)

            val intent = Intent(this, HabitListActivity::class.java)
            startActivity(intent)
        }


        when(v?.id) {
            R.id.exercise_btn -> {
                //Toast.makeText(this, "Exericse", Toast.LENGTH_SHORT).show()
                iconImage.setImageResource(R.drawable.exercise)
                choseIcon = "exercise"
                chosenIconInt = R.drawable.exercise
            }
            R.id.vegetable_btn -> {
                iconImage.setImageResource(R.drawable.vegetable)
                choseIcon = "vegetable"
                chosenIconInt = R.drawable.vegetable
            }
            R.id.water_btn -> {
                iconImage.setImageResource(R.drawable.water)
                choseIcon = "water"
                chosenIconInt = R.drawable.water
            }
            R.id.book_btn -> {
                iconImage.setImageResource(R.drawable.book)
                choseIcon = "book"
                chosenIconInt = R.drawable.book
            }
            R.id.meditate_btn -> {
                iconImage.setImageResource(R.drawable.meditation)
                choseIcon = "meditate"
                chosenIconInt = R.drawable.meditation
            }
            R.id.write_btn -> {
                iconImage.setImageResource(R.drawable.writing)
                choseIcon = "write"
                chosenIconInt = R.drawable.writing
            }
            R.id.cook_btn -> {
                iconImage.setImageResource(R.drawable.cooking)
                choseIcon = "cook"
                chosenIconInt = R.drawable.cooking
            }
            R.id.cleaning_btn -> {
                iconImage.setImageResource(R.drawable.cleaning)
                choseIcon = "clean"
                chosenIconInt = R.drawable.cleaning
            }
        }



    }
}