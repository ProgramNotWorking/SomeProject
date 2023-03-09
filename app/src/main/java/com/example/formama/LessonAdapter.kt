package com.example.formama

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.formama.databinding.LessonItemBinding

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonHolder>() {
    val lessonsList = ArrayList<Lesson>()

    interface OnItemClickListener {
        fun onDeleteClick(item: View)
    }
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class LessonHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = LessonItemBinding.bind(item)

        fun bind(lesson: Lesson) = with(binding) {
            val titleText = "${lesson.lessonNumber + 1} занятие"
            lessonNumberView.text = titleText

            deleteStudentButton.setOnClickListener {
                onItemClickListener?.onDeleteClick(it) // TODO: work on it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.lesson_item, parent, false
        )
        return LessonHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: LessonHolder, position: Int) {
        holder.bind(lessonsList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addLesson(lesson: Lesson) {
        lessonsList.add(lesson)
        notifyDataSetChanged()
    }

    fun removeLesson(index: Int) {
        lessonsList.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, lessonsList.size - 1)
    }
}