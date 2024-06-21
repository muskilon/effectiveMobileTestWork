package com.example.domain.avia.impl

import com.example.domain.avia.DepartureCityInteractor
import com.example.domain.avia.repository.SharedRepository

class DepartureCityInteractorImpl(
    private val repository: SharedRepository
): DepartureCityInteractor {
    override fun getDepartureCity(): String {
        return repository.getDepartureCity()
    }

    override fun setDepartureCity(city: String) {
        repository.setDepartureCity(city)
    }
}