package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsOffersInteractor {
    suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffer>>>
}