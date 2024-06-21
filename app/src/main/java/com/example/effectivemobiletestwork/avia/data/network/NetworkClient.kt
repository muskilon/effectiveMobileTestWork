package com.example.effectivemobiletestwork.avia.data.network

import com.example.effectivemobiletestwork.avia.data.dto.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsOffersDTO
import com.example.domain.Resource

interface NetworkClient {
    suspend fun getRecommendations(): com.example.domain.Resource<MainRecommendationDTO>

    suspend fun getTicketsOffers(): com.example.domain.Resource<TicketsOffersDTO>

    suspend fun getTickets(): com.example.domain.Resource<TicketsDTO>
}