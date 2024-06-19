package com.example.effectivemobiletestwork.avia.data.DTO

class MainRecommendationDTO(
    val offers: List<Offer>
)
data class Offer(
    val id: String,
    val title: String,
    val town: String,
    val price: Price
)