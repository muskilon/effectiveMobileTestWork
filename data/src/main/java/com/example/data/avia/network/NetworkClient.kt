package com.example.data.avia.network

import com.example.data.avia.dto.MainRecommendationDTO
import com.example.data.avia.dto.TicketsDTO
import com.example.data.avia.dto.TicketsOffersDTO

interface NetworkClient {
    suspend fun getRecommendations(): com.example.domain.Resource<MainRecommendationDTO>

    suspend fun getTicketsOffers(): com.example.domain.Resource<TicketsOffersDTO>

    suspend fun getTickets(): com.example.domain.Resource<TicketsDTO>
}