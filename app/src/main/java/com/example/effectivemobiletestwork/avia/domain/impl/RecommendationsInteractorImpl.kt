package com.example.effectivemobiletestwork.avia.domain.impl

import com.example.effectivemobiletestwork.avia.domain.repository.RecommendationRepository
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.flow.Flow

class RecommendationsInteractorImpl(
    private val repository: RecommendationRepository
): RecommendationsInteractor {
    override suspend fun getRecommendations(): Flow<Resource<List<Offer>>> = repository.getRecommendations()
}