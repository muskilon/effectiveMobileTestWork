package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.domain.model.Ticket
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}