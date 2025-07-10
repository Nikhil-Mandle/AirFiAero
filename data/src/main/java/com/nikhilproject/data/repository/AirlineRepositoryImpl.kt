package com.nikhilproject.data.repository

import com.nikhilproject.data.remote.AirlineApi
import com.nikhilproject.domain.model.Airline
import com.nikhilproject.domain.repository.AirlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirlineRepositoryImpl @Inject constructor(
    private val api: AirlineApi
): AirlineRepository {
    override fun getAirlines(): Flow<List<Airline>> = flow {
        emit(api.getAirlines().map { it.toDomain() })
    }
}