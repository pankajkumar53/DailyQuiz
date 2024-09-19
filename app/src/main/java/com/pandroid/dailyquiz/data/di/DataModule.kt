package com.pandroid.dailyquiz.data.di

import com.pandroid.dailyquiz.data.remote.QuizAPI
import com.pandroid.dailyquiz.data.repository.QuizRepositoryImpl
import com.pandroid.dailyquiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideQuizApi(): QuizAPI {
        return Retrofit.Builder().baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(QuizAPI::class.java) /*https://opentdb.com/api.php?amount=10*/
    }

    @Provides
    @Singleton
    fun provideQuizRepository(quizAPI: QuizAPI): QuizRepository {
        return QuizRepositoryImpl(quizAPI)
    }
}