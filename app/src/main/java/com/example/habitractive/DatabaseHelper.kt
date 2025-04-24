package com.example.habitractive

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*
//This is just a normal created kotlin class file
Steps:
1. Declare all constants
2. Extend SQLite open helper
3. Create a table using query
4. OnUpgrade function
5. Create insert user function
6. Read the database
 */


import android.util.Log

class DatabaseHelper(private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {  //you can access this object using the class name itself without creating an instance of the class

        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ICON = "icon"
        private const val COLUMN_HABIT_NAME = "Habit Name"
        private const val COLUMN_HABIT_COUNT = "String Count"
        private const val COLUMN_REP_COUNT = "Rep Count"
        private const val COLUMN_ADD_COUNT = "Add Count"
    }

    //create table with the new columns
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_ICON INTEGER, " +
                "$COLUMN_HABIT_NAME TEXT, " +
                "$COLUMN_HABIT_COUNT TEXT, " +
                "$COLUMN_REP_COUNT INTEGER, " +
                "$COLUMN_ADD_COUNT INTEGER)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME" //remove table from database if exist
        db?.execSQL(dropTableQuery)
        onCreate(db) //to create new table
    }

    // This function has 5 parameters to insert a new habit
    fun insertHabit(icon: Int, habitName: String, habitCount: String, repCount: Int, addCount: Int): Long {
        val values = ContentValues().apply {
            put(COLUMN_ICON, icon)
            put(COLUMN_HABIT_NAME, habitName)
            put(COLUMN_HABIT_COUNT, habitCount)
            put(COLUMN_REP_COUNT, repCount)
            put(COLUMN_ADD_COUNT, addCount)
        }
        val db = writableDatabase //allows data modification
        return db.insert(TABLE_NAME, null, values) //inserts the values and returns
    }
}



    //------------------------------------------------------------------------------------------//

