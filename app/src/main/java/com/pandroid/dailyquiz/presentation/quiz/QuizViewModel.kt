package com.pandroid.dailyquiz.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandroid.dailyquiz.common.Resource
import com.pandroid.dailyquiz.domain.model.Quiz
import com.pandroid.dailyquiz.domain.usecase.GetQuizzesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val getQuizzesUseCases: GetQuizzesUseCases) :
    ViewModel() {

    private val _quizList = MutableStateFlow(
        StateQuizScreen()
    )

    val quizList = _quizList

    fun onEvent(event: EventQuizScreen) {
        when (event) {
            is EventQuizScreen.GetQuizzes -> {
                getQuizzes(event.numOfQuizzes, event.category, event.difficulty, event.type)
            }

            is EventQuizScreen.SetOptionsSelected -> {
                updateQuizStateList(event.quizStateIndex, event.selectedOption)
            }
        }
    }

    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {
        val updatedQuizStateList = mutableListOf<QuizState>()
        quizList.value.quizState.forEachIndexed { index, quizState ->
            updatedQuizStateList.add(
                if (quizStateIndex == index) {
                    quizState.copy(selectedOption = selectedOption)
                } else {
                    quizState
                }
            )
        }
        _quizList.value = quizList.value.copy(quizState = updatedQuizStateList)


        updateScore(_quizList.value.quizState[quizStateIndex])

    }

    private fun updateScore(quizState: QuizState) {

        if (quizState.selectedOption != -1) {
            val correctAnswer: String? = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOption?.let {
                quizState.shuffledOptions[it].replace("&quot", "\"").replace("&#039;", "\"")
            }
            Log.d("CHECK ANSWER", "updateScore: $correctAnswer -> $selectedAnswer")

            if (correctAnswer == selectedAnswer) {
                val previousScore = _quizList.value.score
                _quizList.value = quizList.value.copy(score = previousScore + 1)
            }
        }


    }

    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            getQuizzesUseCases(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = StateQuizScreen(isLoading = true)
                    }

                    is Resource.Success -> {

                        val listOfQuizState: List<QuizState> = getListOfQuizState(resource.data)
                        _quizList.value = StateQuizScreen(quizState = listOfQuizState)
                    }

                    is Resource.Error -> {
                        _quizList.value = StateQuizScreen(error = resource.message.toString())
                    }
                }
            }
        }

    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {
        val listOfQuizState = mutableListOf<QuizState>()

        for (quiz in data!!) {
            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer)
                addAll(quiz.incorrect_answers)
                shuffle()
            }
            listOfQuizState.add(QuizState(quiz, shuffledOptions, -1))
        }

        return listOfQuizState
    }

}