package com.nikhilproject.airfiaero.di

import com.nikhilproject.domain.repository.AirlineRepository
import com.nikhilproject.domain.usecase.GetAirlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAirlinesUseCase(
        repository: AirlineRepository
    ): GetAirlinesUseCase = GetAirlinesUseCase(repository)
}