package com.example.domain.avia.impl

import com.example.domain.avia.TicketsOffersInteractor
import com.example.domain.avia.repository.TicketsOffersRepository
import com.example.domain.avia.model.TicketsOffer
import kotlinx.coroutines.flow.Flow

class TicketsOffersInteractorImpl(
    private val repository: TicketsOffersRepository
): TicketsOffersInteractor {
    override suspend fun getTicketsOffers(): Flow<com.example.domain.Resource<List<TicketsOffer>>> = repository.getTicketsOffers()
}