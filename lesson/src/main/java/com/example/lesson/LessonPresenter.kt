package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient.get
import com.example.core.utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * @author  chy
 * @date    2020-04-04
 */
class LessonPresenter {

    companion object {
        const val LESSON_PATH = "lessons"
    }

    private lateinit var activity: LessonActivity

    constructor(activity: LessonActivity) {
        this.activity = activity
    }

   var lessons: List<Lesson> = ArrayList()
    private val type: Type = object : TypeToken<List<Lesson?>?>() {}.type
    fun fetchData() {
        get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(lessons: List<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread {toast(message) }
            }
        })
    }

    fun showPlayback() {
        val playbackLessons: MutableList<Lesson> = ArrayList()
        for (lesson in lessons) {
            if (lesson.getState() === Lesson.State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }
}