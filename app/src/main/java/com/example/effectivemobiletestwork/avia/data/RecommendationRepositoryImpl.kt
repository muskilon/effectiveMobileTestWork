package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.dto.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.data.network.NetworkClient
import com.example.domain.avia.model.Offer
import com.example.domain.avia.repository.RecommendationRepository
import com.example.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecommendationRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DTOToDataMappers
    ) : RecommendationRepository {
        override suspend fun getRecommendations(): Flow<com.example.domain.Resource<List<Offer>>> = flow {
            when (val response = networkClient.getRecommendations()) {
                is com.example.domain.Resource.Data -> {
                    with(response) {
                        val data = mapper.recommendationsDTOToMainRecommendations(this.value.offers)
                        emit(com.example.domain.Resource.Data(data))
                    }
                }

                is com.example.domain.Resource.NotFound -> emit(com.example.domain.Resource.NotFound(response.message))
                is com.example.domain.Resource.ConnectionError -> {
                    emit(com.example.domain.Resource.ConnectionError(response.message))
                }
            }
        }.flowOn(Dispatchers.IO)
    }