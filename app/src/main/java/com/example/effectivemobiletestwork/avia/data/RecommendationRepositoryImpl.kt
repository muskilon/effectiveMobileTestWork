package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.DTO.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.domain.model.Offer
import com.example.effectivemobiletestwork.avia.domain.RecommendationRepository
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecommendationRepositoryImpl(
        private val networkClient: NetworkClient,
        private val mapper: DTOToDataMappers
    ) : RecommendationRepository {
        override suspend fun getRecommendations(): Flow<Resource<List<Offer>>> = flow {
            when (val response = networkClient.getRecommendations()) {
                is Resource.Data -> {
                    with(response) {
                        val data = mapper.recommendationsDTOToMainRecommendations(this.value.offers)
                        emit(Resource.Data(data))
                    }
                }

                is Resource.NotFound -> emit(Resource.NotFound(response.message))
                is Resource.ConnectionError -> {
                    emit(Resource.ConnectionError(response.message))
                }
            }
        }.flowOn(Dispatchers.IO)
    }