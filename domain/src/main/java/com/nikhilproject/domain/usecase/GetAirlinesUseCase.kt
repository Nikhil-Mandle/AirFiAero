package com.nikhilproject.domain.usecase

import com.nikhilproject.domain.repository.AirlineRepository

class GetAirlinesUseCase(
    private val repository: AirlineRepository
) {
    operator fun invoke() = repository.getAirlines()
}