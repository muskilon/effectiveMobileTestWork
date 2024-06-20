package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.dto.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsOffersDTO
import com.example.effectivemobiletestwork.domain.Resource

interface NetworkClient {
    suspend fun getRecommendations(): Resource<MainRecommendationDTO>

    suspend fun getTicketsOffers(): Resource<TicketsOffersDTO>

    suspend fun getTickets(): Resource<TicketsDTO>
}