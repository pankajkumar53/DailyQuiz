package com.pandroid.dailyquiz.data.repository

import com.pandroid.dailyquiz.data.remote.QuizAPI
import com.pandroid.dailyquiz.domain.model.Quiz
import com.pandroid.dailyquiz.domain.repository.QuizRepository

class QuizRepositoryImpl(private val quizAPI: QuizAPI): QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizAPI.getQuizzes(amount, category, difficulty , type).results
    }
}