package com.example.data.avia.dto

data class MainRecommendationDTO(
    val offers: List<OfferDTO>
)
data class OfferDTO(
    val id: String,
    val title: String,
    val town: String,
    val price: Price
)