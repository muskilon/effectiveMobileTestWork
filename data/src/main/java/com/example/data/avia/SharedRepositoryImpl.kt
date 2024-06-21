package com.example.data.avia

import com.example.domain.avia.repository.SharedRepository

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