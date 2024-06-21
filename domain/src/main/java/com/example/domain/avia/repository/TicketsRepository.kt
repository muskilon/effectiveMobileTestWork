package com.example.domain.avia.repository

import com.example.domain.Resource
import com.example.domain.avia.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun getTickets(): Flow<Resource<List<Ticket>>>
}