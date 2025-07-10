package com.nikhilproject.data.model

import com.nikhilproject.domain.model.Airline

data class AirlineDto(
    val id: String,
    val name: String,
    val country: String,
    val headquarters: String,
    val fleet_size: Int,
    val website: String,
    val logo_url: String
) {
    fun toDomain(): Airline {
        return Airline(
            id = id,
            name = name,
            country = country,
            headquarters = headquarters,
            fleetSize = fleet_size,
            website = website,
            logoUrl = logo_url
        )
    }
}
