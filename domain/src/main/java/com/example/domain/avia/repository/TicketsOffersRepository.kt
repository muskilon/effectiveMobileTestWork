package com.example.domain.avia.repository

import com.example.domain.avia.model.TicketsOffer
import com.example.domain.Resource
import kotlinx.coroutines.flow.Flow

interface TicketsOffersRepository {

    suspend fun getTicketsOffers(): Flow<com.example.domain.Resource<List<TicketsOffer>>>
}