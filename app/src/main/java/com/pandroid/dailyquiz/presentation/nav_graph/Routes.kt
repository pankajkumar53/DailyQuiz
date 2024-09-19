package com.pandroid.dailyquiz.presentation.nav_graph

const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"

sealed class Routes(val route: String) {

    object HomeScreen : Routes(route = "home_screen")

    object QuizScreen :
        Routes(route = "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}") {

        fun passQuizParams(
            numOfQuizzes: Int,
            category: String,
            difficulty: String,
            type: String
        ): String {
            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace("{${ARG_KEY_QUIZ_NUMBER}}", numOfQuizzes.toString())
                .replace("{${ARG_KEY_QUIZ_CATEGORY}}", category)
                .replace("{${ARG_KEY_QUIZ_DIFFICULTY}}", difficulty)
                .replace("{${ARG_KEY_QUIZ_TYPE}}", type)
        }
    }

    object ScoreScreen : Routes(route = "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}") {
        fun passNumOfQuestionsAndCorrectAns(questions: Int, correctAnswers: Int): String {
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"
                .replace("{$NOQ_KEY}", questions.toString())
                .replace("{$CORRECT_ANS_KEY}", correctAnswers.toString())
        }
    }
}
