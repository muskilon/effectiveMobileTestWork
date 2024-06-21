package com.example.domain.avia.impl

import com.example.domain.avia.TicketsInteractor
import com.example.domain.avia.repository.TicketsRepository
import com.example.domain.avia.model.Ticket
import kotlinx.coroutines.flow.Flow

class TicketsInteractorImpl(
    private val repository: TicketsRepository
): TicketsInteractor {
    override suspend fun getTickets(): Flow<com.example.domain.Resource<List<Ticket>>> = repository.getTickets()
}