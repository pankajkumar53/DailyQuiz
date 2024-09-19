package com.pandroid.dailyquiz.data.remote.dto

import com.pandroid.dailyquiz.domain.model.Quiz

data class QuizResponce(
    val response_code: Int,
    val results: List<Quiz>
)