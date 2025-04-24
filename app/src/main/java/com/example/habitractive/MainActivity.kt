package com.example.habitractive

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var usernameText : TextView
    lateinit var passwordText : TextView
    lateinit var loginBtn : TextView

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        usernameText = findViewById(R.id.username_input)
        passwordText = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        //Login in codes
        loginBtn.setOnClickListener {

            val username = usernameText.text.toString()
            val password = passwordText.text.toString()

            if(username == "habit" && password == "123") { //username == "habit" && password == "habit123"
                val intent = Intent(this, HabitListActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Access Denied", Toast.LENGTH_SHORT).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}