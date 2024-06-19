package com.example.effectivemobiletestwork.avia.domain

import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

class RecommendationsInteractorImpl(
    private val repository: RecommendationRepository
): RecommendationsInteractor {
    override suspend fun getRecommendations(): Flow<Resource<List<Offer>>> = repository.getRecommendations()
}