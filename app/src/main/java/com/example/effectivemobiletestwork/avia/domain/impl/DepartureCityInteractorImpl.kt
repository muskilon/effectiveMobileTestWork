package com.example.effectivemobiletestwork.avia.domain.impl

import com.example.effectivemobiletestwork.avia.domain.DepartureCityInteractor
import com.example.effectivemobiletestwork.avia.domain.repository.SharedRepository

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