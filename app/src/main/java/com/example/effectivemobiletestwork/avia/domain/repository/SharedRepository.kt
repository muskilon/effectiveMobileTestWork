package com.example.effectivemobiletestwork.avia.domain.repository


interface SharedRepository {
    fun getDepartureCity(): String
    fun setDepartureCity(city: String)
}