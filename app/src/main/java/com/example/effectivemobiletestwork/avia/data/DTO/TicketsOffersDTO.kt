package com.example.effectivemobiletestwork.avia.data.DTO

import com.google.gson.annotations.SerializedName

data class TicketsOffersDTO(
    @SerializedName("tickets_offers")
    val ticketsOffers: ArrayList<TicketsOffers>
)

data class TicketsOffers(
    val id: String,
    var title: String,
    @SerializedName("time_range")
    val timeRange: ArrayList<String>,
    val price: Price = Price()
)

data class Price(
    val value: Int? = null
)