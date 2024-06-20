package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.data.dto.TicketDTO
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsInteractor {
    suspend fun getTickets(): Flow<Resource<List<TicketDTO>>>
}