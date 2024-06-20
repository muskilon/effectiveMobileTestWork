package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

class TicketsOffersInteractorImpl(
    private val repository: TicketsOffersRepository
): TicketsOffersInteractor {
    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffer>>> = repository.getTicketsOffers()
}