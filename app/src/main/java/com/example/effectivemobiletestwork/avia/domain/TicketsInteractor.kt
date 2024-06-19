package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.data.DTO.Ticket
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsOffers
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsInteractor {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
    suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffers>>>
}