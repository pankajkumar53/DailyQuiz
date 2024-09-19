package com.pandroid.dailyquiz.domain.repository

import com.pandroid.dailyquiz.domain.model.Quiz

interface QuizRepository {
    suspend fun getQuizzes(amount: Int, category: Int,difficulty: String, type: String) : List<Quiz>
}