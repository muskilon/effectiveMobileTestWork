package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.data.DTO.Ticket
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsOffers
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

class TicketsInteractorImpl(
    private val repository: TicketsRepository
): TicketsInteractor {
    override suspend fun getTickets(): Flow<Resource<List<Ticket>>> = repository.getTickets()

    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffers>>> = repository.getTicketsOffers()
}