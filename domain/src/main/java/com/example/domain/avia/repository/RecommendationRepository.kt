package com.example.domain.avia.repository

import com.example.domain.Resource
import com.example.domain.avia.model.Offer
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    suspend fun getRecommendations(): Flow<Resource<List<Offer>>>
}