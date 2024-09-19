package com.pandroid.dailyquiz.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel : ViewModel() {

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState

    fun onEvent(event: EventHomeScreen) {

        when(event) {

            is EventHomeScreen.SetNoOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)
            }


            is EventHomeScreen.SetQuizCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)
            }

            is EventHomeScreen.SetQuizDifficulty -> {
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }

            is EventHomeScreen.SetQuizType -> {
                _homeState.value = homeState.value.copy(type = event.type)
            }

            else -> {}
        }
    }
}