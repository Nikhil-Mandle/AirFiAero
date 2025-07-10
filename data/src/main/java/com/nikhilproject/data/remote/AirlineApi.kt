package com.nikhilproject.data.remote

import com.nikhilproject.data.model.AirlineDto
import com.nikhilproject.data.utils.Constants.GET_AIRLINES
import retrofit2.http.GET

interface AirlineApi {

    @GET(GET_AIRLINES)
    suspend fun getAirlines(): List<AirlineDto>
}