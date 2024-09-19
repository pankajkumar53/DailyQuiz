package com.pandroid.dailyquiz.domain.di

import com.pandroid.dailyquiz.domain.repository.QuizRepository
import com.pandroid.dailyquiz.domain.usecase.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository) :GetQuizzesUseCases {
        return GetQuizzesUseCases(quizRepository)
    }
}