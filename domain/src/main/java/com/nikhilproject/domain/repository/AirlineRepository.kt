package com.nikhilproject.domain.repository

import com.nikhilproject.domain.model.Airline
import kotlinx.coroutines.flow.Flow

interface AirlineRepository {

    fun getAirlines(): Flow<List<Airline>>
}