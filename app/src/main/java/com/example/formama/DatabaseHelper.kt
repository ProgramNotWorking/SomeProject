package com.example.formama

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_QUERY)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "my_database"
        private const val DATABASE_VERSION = 1
        private const val CREATE_TABLE_QUERY = "CREATE TABLE my_table (id INTEGER PRIMARY KEY, name TEXT)"
        private const val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS my_table"
    }
}
