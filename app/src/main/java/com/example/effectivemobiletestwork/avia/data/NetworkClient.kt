package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.DTO.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsOffersDTO
import com.example.effectivemobiletestwork.domain.Resource

interface NetworkClient {
    suspend fun getRecommendations(): Resource<MainRecommendationDTO>

    suspend fun getTicketsOffers(): Resource<TicketsOffersDTO>

    suspend fun getTickets(): Resource<TicketsDTO>
}