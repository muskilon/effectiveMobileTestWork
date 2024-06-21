package com.example.domain.avia.repository

import com.example.domain.avia.model.Offer
import com.example.domain.Resource
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    suspend fun getRecommendations(): Flow<com.example.domain.Resource<List<Offer>>>
}