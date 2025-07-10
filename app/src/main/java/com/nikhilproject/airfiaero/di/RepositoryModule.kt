package com.nikhilproject.airfiaero.di

import com.nikhilproject.data.remote.AirlineApi
import com.nikhilproject.data.repository.AirlineRepositoryImpl
import com.nikhilproject.domain.repository.AirlineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAirlineRepository(
        api: AirlineApi
    ): AirlineRepository = AirlineRepositoryImpl(api)
}
