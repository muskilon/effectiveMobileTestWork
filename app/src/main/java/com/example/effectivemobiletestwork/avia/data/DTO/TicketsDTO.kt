package com.example.effectivemobiletestwork.avia.data.DTO

import com.google.gson.annotations.SerializedName

data class TicketsDTO(
    val tickets: List<Ticket>
)

data class Departure(
    val town: String,
    val date: String,
    val airport: String
)
data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)
data class Luggage (
    @SerializedName("has_luggage" )
    val hasLuggage: Boolean,
    val price: Price
)
data class HandLuggage (
    @SerializedName("has_hand_luggage" )
    val hasHandLuggage: Boolean,
    val size: String
)
data class Ticket (
    val id: Int,
    val badge: String,
    val price: Price,
    @SerializedName("provider_name")
    val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerializedName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerializedName("is_returnable")
    var isReturnable: Boolean,
    @SerializedName("is_exchangable")
    val isExchangable: Boolean
)