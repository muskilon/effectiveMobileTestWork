package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.DTO.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.data.DTO.TicketOffersDTO
import com.example.effectivemobiletestwork.avia.domain.TicketsOffersRepository
import com.example.effectivemobiletestwork.avia.domain.model.TicketsOffer
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketsOffersRepositoryImpl(
private val networkClient: NetworkClient,
private val mapper: DTOToDataMappers
) : TicketsOffersRepository {
    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffer>>> = flow {
        when (val response = networkClient.getTicketsOffers()) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.ticketsOffersDTOToTicketsOffers(this.value.ticketsOffers)
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