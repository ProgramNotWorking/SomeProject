package com.example.formama

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDao {

    @Insert
    fun insertAll(vararg student: Student)

    @Query("SELECT * FROM student")
    fun getAllUser(): List<Student>

}