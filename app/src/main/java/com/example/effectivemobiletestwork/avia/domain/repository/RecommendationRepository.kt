package com.example.effectivemobiletestwork.avia.domain.repository

import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    suspend fun getRecommendations(): Flow<Resource<List<Offer>>>
}