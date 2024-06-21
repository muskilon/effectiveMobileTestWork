package com.example.domain.avia.repository


interface SharedRepository {
    fun getDepartureCity(): String
    fun setDepartureCity(city: String)
}