package com.example.domain.avia.impl

import com.example.domain.avia.repository.RecommendationRepository
import com.example.domain.avia.RecommendationsInteractor
import com.example.domain.avia.model.Offer
import kotlinx.coroutines.flow.Flow

class RecommendationsInteractorImpl(
    private val repository: RecommendationRepository
): RecommendationsInteractor {
    override suspend fun getRecommendations(): Flow<com.example.domain.Resource<List<Offer>>> = repository.getRecommendations()
}