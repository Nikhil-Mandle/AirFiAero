package com.nikhilproject.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object AirlineListScreenNav

@Serializable
data class AirlineDetailScreenNav(val airlineId: String)