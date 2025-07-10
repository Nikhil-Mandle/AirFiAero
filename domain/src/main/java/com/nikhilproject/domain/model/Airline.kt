package com.nikhilproject.domain.model

data class Airline(
    val id: String,
    val name: String,
    val country: String,
    val headquarters: String,
    val fleetSize: Int,
    val website: String,
    val logoUrl: String
)

