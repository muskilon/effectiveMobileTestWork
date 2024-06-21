package com.example.domain.avia

import com.example.domain.avia.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsInteractor {
    suspend fun getTickets(): Flow<com.example.domain.Resource<List<Ticket>>>
}