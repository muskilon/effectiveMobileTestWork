package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

interface RecommendationsInteractor {
    suspend fun getRecommendations(): Flow<Resource<List<MainRecommendation>>>
}