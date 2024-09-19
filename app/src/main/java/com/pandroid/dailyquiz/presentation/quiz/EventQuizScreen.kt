package com.pandroid.dailyquiz.presentation.quiz

sealed class EventQuizScreen {
    data class GetQuizzes(
        val numOfQuizzes: Int,
        val category: Int,
        val difficulty: String,
        val type: String
    ) : EventQuizScreen()


    data class SetOptionsSelected(val quizStateIndex: Int, val selectedOption: Int) :
        EventQuizScreen()
}



