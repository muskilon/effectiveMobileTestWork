package com.example.effectivemobiletestwork.avia.data.dto

import com.google.gson.annotations.SerializedName

data class TicketsOffersDTO(
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketOffersDTO>
)

data class TicketOffersDTO(
    val id: String,
    val title: String,
    @SerializedName("time_range")
    val timeRange: ArrayList<String>,
    val price: Price
)

data class Price(
    val value: String
)