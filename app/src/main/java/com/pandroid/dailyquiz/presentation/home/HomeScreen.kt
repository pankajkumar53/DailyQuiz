package com.pandroid.dailyquiz.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.pandroid.dailyquiz.presentation.common.AppDropDownMenu
import com.pandroid.dailyquiz.presentation.common.ButtonBox
import com.pandroid.dailyquiz.presentation.home.component.HomeHeader
import com.pandroid.dailyquiz.presentation.nav_graph.Routes
import com.pandroid.dailyquiz.presentation.util.Constants
import com.pandroid.dailyquiz.presentation.util.Constants.difficulty
import com.pandroid.dailyquiz.presentation.util.Dimens.MediumPadding
import com.pandroid.dailyquiz.presentation.util.Dimens.MediumSpacerHeight
import com.pandroid.dailyquiz.presentation.util.Dimens.SmallSpacerHeight

/*
   @Preview
   @Composable
   fun PrevHome() {
    HomeScreen(state = StateHomeScreen(), event = {})
   }
*/


@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (EventHomeScreen) -> Unit,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        HomeHeader()

        Spacer(modifier = Modifier.height(MediumSpacerHeight))
        AppDropDownMenu(
            menuName = "Numbers of Questions: ",
            menuList = Constants.numbersAsString,
            text = state.numberOfQuiz.toString(),
            onDropDownClick = {event(EventHomeScreen.SetNoOfQuizzes(it.toInt()))})


        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Category: ",
            menuList = Constants.categories,
            text = state.category,
            onDropDownClick = {event(EventHomeScreen.SetQuizCategory(it))})


        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty: ",
            menuList = difficulty,
            text = state.difficulty,
            onDropDownClick = {event(EventHomeScreen.SetQuizDifficulty(it))})


        Spacer(modifier = Modifier.height(SmallSpacerHeight))
        AppDropDownMenu(
            menuName = "Select Type: ",
            menuList = Constants.type,
            text = state.type,
            onDropDownClick = {event(EventHomeScreen.SetQuizType(it))})

        Spacer(modifier = Modifier.height(MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = MediumPadding) {
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(state.numberOfQuiz,state.category,state.difficulty,state.type)
            )
        }

    }
}

