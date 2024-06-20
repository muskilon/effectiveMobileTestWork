package com.example.effectivemobiletestwork.avia.domain.model

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val departureDate: String,
    val departureAirport: String,
    val timeInFlight: String,
    val transfer: Boolean
)