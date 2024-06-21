package com.example.domain.avia

import com.example.domain.avia.model.TicketsOffer
import kotlinx.coroutines.flow.Flow

interface TicketsOffersInteractor {
    suspend fun getTicketsOffers(): Flow<com.example.domain.Resource<List<TicketsOffer>>>
}