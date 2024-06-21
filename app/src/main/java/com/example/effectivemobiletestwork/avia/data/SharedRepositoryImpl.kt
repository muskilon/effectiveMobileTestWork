package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.domain.repository.SharedRepository

class SharedRepositoryImpl(
    private val sharedStorage: SharedStorage
) : SharedRepository {
    override fun getDepartureCity(): String {
        return sharedStorage.getDepartureCity() ?: String()
    }

    override fun setDepartureCity(city: String) {
        return sharedStorage.setDepartureCity(city)
    }
}