package com.example.domain.avia

import com.example.domain.avia.model.Offer
import kotlinx.coroutines.flow.Flow

interface RecommendationsInteractor {
    suspend fun getRecommendations(): Flow<com.example.domain.Resource<List<Offer>>>
}