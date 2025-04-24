package com.example.habitractive

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HabitInfoActivity : AppCompatActivity() {

    lateinit var returnBtn : Button
    lateinit var addBtn : ImageButton
    lateinit var resetBtn : Button
    lateinit var deleteBtn : Button

    lateinit var habitNameInfo : TextView
    lateinit var repetitionCount : TextView

    lateinit var iconImageView : ImageView

    var addHabitCount : Int = 0

    var habitCountInt : Int = 0

    var habitArrPosition : Int = 0

    lateinit var addSound : MediaPlayer
    lateinit var succesSound : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_habit_info)

        returnBtn = findViewById(R.id.return_btn)
        addBtn = findViewById(R.id.add_btn)
        resetBtn = findViewById(R.id.reset_btn)
        deleteBtn = findViewById(R.id.delete_btn)

        habitNameInfo = findViewById(R.id.habitNameInfo)
        repetitionCount = findViewById(R.id.repetitionCount)

        iconImageView = findViewById(R.id.iconImageView)

        //access variables passed from HabitListActivity
        habitArrPosition = intent.getIntExtra("HabitArrPos", 0)

        val habitNameString = intent.getStringExtra("ItemPos")
        val habitRepString = intent.getStringExtra("RepetitionPos")
        habitCountInt = intent.getIntExtra("RepIntPos", 0)

        habitNameInfo.text = habitNameString

        addSound = MediaPlayer.create(this, R.raw.addsound)
        succesSound = MediaPlayer.create(this, R.raw.success)

        //Adjust the variable to be added to reflect
        addHabitCount = HabitListActivity.habitListVariables.habitAddedCountArr[habitArrPosition]

        repetitionCount.text = "$addHabitCount out of $habitCountInt left"

        iconImageView.setImageResource(HabitListActivity.habitListVariables.iconArr[habitArrPosition])


        returnBtn.setOnClickListener {

            //Adjust the value of the repetition count on the arrayList variable to the corresponding position
            HabitListActivity.habitListVariables.habitAddedCountArr[habitArrPosition] = addHabitCount

            val intent = Intent(this, HabitListActivity::class.java)
            startActivity(intent)

        }

        addBtn.setOnClickListener {

            if(addHabitCount == habitCountInt - 1) {

                succesSound.start()

                repetitionCount.text = "$addHabitCount out of $habitCountInt left"

                addHabitCount = 0

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("You have completed your habit task")
                    .setTitle("Congratulations")

                    .setPositiveButton("Okay") { dialog, which -> //use this to accept and continue with the action (the "OK" action)
                        repetitionCount.text = "$addHabitCount out of $habitCountInt left"

                        HabitListActivity.habitListVariables.iconArr.removeAt(habitArrPosition)
                        HabitListActivity.habitListVariables.habitNameArr.removeAt(habitArrPosition)
                        HabitListActivity.habitListVariables.habitCountArr.removeAt(habitArrPosition)
                        HabitListActivity.habitListVariables.habitRepCountArr.removeAt(habitArrPosition)
                        HabitListActivity.habitListVariables.habitAddedCountArr.removeAt(habitArrPosition)

                        val intent = Intent(this, HabitListActivity::class.java)
                        startActivity(intent)
                    }

                    .setNegativeButton("Reset") { dialog, which -> //use this to cancel the action.
                        addHabitCount = 0

                        repetitionCount.text = "$addHabitCount out of $habitCountInt left"
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()


            }else{
                addSound.start()

                addHabitCount += 1
                repetitionCount.text = "$addHabitCount out of $habitCountInt left"
            }


        }

        resetBtn.setOnClickListener {

            addHabitCount = 0

            repetitionCount.text = "$addHabitCount out of $habitCountInt left"

        }

        deleteBtn.setOnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Are you sure you want to delete this habit task?")
                .setTitle("Message")

                .setPositiveButton("Yes") { dialog, which -> //use this to accept and continue with the action (the "OK" action)

                    HabitListActivity.habitListVariables.iconArr.removeAt(habitArrPosition)
                    HabitListActivity.habitListVariables.habitNameArr.removeAt(habitArrPosition)
                    HabitListActivity.habitListVariables.habitCountArr.removeAt(habitArrPosition)
                    HabitListActivity.habitListVariables.habitRepCountArr.removeAt(habitArrPosition)
                    HabitListActivity.habitListVariables.habitAddedCountArr.removeAt(habitArrPosition)

                    val intent = Intent(this, HabitListActivity::class.java)
                    startActivity(intent)
                }

                .setNegativeButton("No") { dialog, which -> //use this to cancel the action.

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}