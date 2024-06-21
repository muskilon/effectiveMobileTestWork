package com.example.domain.avia.repository

import com.example.domain.Resource
import com.example.domain.avia.model.TicketsOffer
import kotlinx.coroutines.flow.Flow

interface TicketsOffersRepository {

    suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffer>>>
}