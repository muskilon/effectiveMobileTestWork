package com.example.domain.avia.repository

import com.example.domain.avia.model.Ticket
import com.example.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun getTickets(): Flow<com.example.domain.Resource<List<Ticket>>>
}