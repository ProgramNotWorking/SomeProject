package com.example.formama

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.iterator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lessonIndex = 0
    private val adapter = LessonAdapter()

    private val dbHelper = DatabaseHelper(this)

    private val namesList = arrayListOf<String>()
    private val timeList = arrayListOf<String>()
    private val daysList = arrayListOf<String>()

    lateinit var deletedStudentName: String
    lateinit var deletedStudentTime: String

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.setOnItemClickListener(object: LessonAdapter.OnItemClickListener {
            override fun onDeleteClick(item: View) {
                deletedStudentName = item.findViewById<EditText>(R.id.editNameField).text.toString()
                deletedStudentTime = item.findViewById<EditText>(R.id.editTimeField).text.toString()
            }
        })

        // val dbObject = dbHelper.writableDatabase

        var whatDayIndex = 0
        val recycleViewsList = listOf(
            binding.mondayRcView,
            binding.tuesdayRcView,
            binding.wednesdayRcView,
            binding.thursdayRcView,
            binding.fridayRcView,
            binding.saturdayRcView
        )


        binding.apply {
            for (rcView in recycleViewsList) {
                rcView.layoutManager = GridLayoutManager(this@MainActivity, 1)
                rcView.adapter = adapter
            }

            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.mondayId -> {
                        chooseRequiredDay(0, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 0
                    }
                    R.id.tuesdayId -> {
                        chooseRequiredDay(1, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 1
                    }
                    R.id.wednesdayId -> {
                        chooseRequiredDay(2, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 2
                    }
                    R.id.thursdayId -> {
                        chooseRequiredDay(3, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 3
                    }
                    R.id.fridayId -> {
                        chooseRequiredDay(4, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 4
                    }
                    R.id.saturdayId -> {
                        chooseRequiredDay(5, recycleViewsList)
                        clearRcView()
                        whatDayIndex = 5
                    }
                    R.id.allDaysId -> {

                    }
                }
                drawer.closeDrawer(GravityCompat.START)

                true
            }

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.deleteLessonId -> {
//                        if (adapter.lessonsList.size != 0) {
//                            //adapter.removeLesson()
//                            lessonIndex -= 1
//
//
//                        }

                        Log.d("deleted name", deletedStudentName)
                        Log.d("deleted time", deletedStudentTime)

                        // TODO: and after that do delete useful(not what like now)
                    }
                    R.id.addLessonId -> {
                        val lesson = Lesson(lessonIndex)
                        lessonIndex++
                        adapter.addLesson(lesson)
                    }
                    R.id.saveId -> {
                        saveData(recycleViewsList, whatDayIndex)
                        Toast.makeText(
                            this@MainActivity, "Saved", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                true
            }
        }
    }

    @SuppressLint("Recycle")
    private fun tempTestDBFun() {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            for (item in namesList) {
                put("name", item)
            }
        }
        db.insert("my_table", null, values)

        val projection = arrayOf("id", "name")
        val cursor = db.query(
            "my_table",
            projection,
            null,
            null,
            null,
            null,
            null
        )

        cursor.close()
        db.close()
    }

    private fun saveData(rcViewList: List<RecyclerView>, whatDay: Int) {
        var lessonsCount = 0
        fun saveNamesAndTime() {
            for (item in rcViewList[whatDay]) {
                namesList.add(item.findViewById<EditText>(R.id.editNameField).text.toString())
                timeList.add(item.findViewById<EditText>(R.id.editTimeField).text.toString())
                lessonsCount++
            }
        }

        fun saveDays(lessonsCount: Int) {
            for (item in 0 until lessonsCount) {
                when (whatDay) {
                    0 ->
                        daysList.add(DaysConstance.MONDAY)
                    1 ->
                        daysList.add(DaysConstance.TUESDAY)
                    2 ->
                        daysList.add(DaysConstance.WEDNESDAY)
                    3 ->
                        daysList.add(DaysConstance.THURSDAY)
                    4 ->
                        daysList.add(DaysConstance.FRIDAY)
                    5 ->
                        daysList.add(DaysConstance.SATURDAY)
                }
            }
        }

        saveNamesAndTime()
        saveDays(lessonsCount)
    }

    private fun clearRcView() {
        binding.apply {
            for (item in 0 until lessonIndex) {
                adapter.removeLesson(item)
            }
            lessonIndex = 0
        }
    }

    private fun chooseRequiredDay(index: Int, recycleViewsList: List<RecyclerView>) {
        binding.apply {
            when (index) {
                0 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
                1 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
                2 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
                3 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
                4 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
                5 -> {
                    for (i in 0..5) {
                        if (i == index) {
                            recycleViewsList[i].visibility = View.VISIBLE
                        } else {
                            recycleViewsList[i].visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}