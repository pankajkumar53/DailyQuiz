package com.pandroid.dailyquiz.data.remote

import com.pandroid.dailyquiz.data.remote.dto.QuizResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizAPI {
    @GET("api.php/")
    suspend fun getQuizzes(
       @Query("amount") amount : Int,
       @Query("category") category : Int,
       @Query("difficulty") difficulty : String,
       @Query("type") type : String
    ) : QuizResponce
}