package com.example.effectivemobiletestwork.avia.domain.impl

import com.example.effectivemobiletestwork.avia.domain.TicketsInteractor
import com.example.effectivemobiletestwork.avia.domain.repository.TicketsRepository
import com.example.effectivemobiletestwork.avia.domain.model.Ticket
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

class TicketsInteractorImpl(
    private val repository: TicketsRepository
): TicketsInteractor {
    override suspend fun getTickets(): Flow<Resource<List<Ticket>>> = repository.getTickets()
}