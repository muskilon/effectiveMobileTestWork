package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.DTO.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.data.DTO.Ticket
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsOffers
import com.example.effectivemobiletestwork.avia.domain.TicketsRepository
import com.example.effectivemobiletestwork.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketsRepositoryImpl(
private val networkClient: NetworkClient,
private val mapper: DTOToDataMappers
) : TicketsRepository {
    override suspend fun getTickets(): Flow<Resource<List<Ticket>>> = flow {
        when (val response = networkClient.getTickets()) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.ticketsDTOToTickets(this.value.tickets)
                    emit(Resource.Data(data))
                }
            }
            is Resource.NotFound -> emit(Resource.NotFound(response.message))
            is Resource.ConnectionError -> {
                emit(Resource.ConnectionError(response.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getTicketsOffers(): Flow<Resource<List<TicketsOffers>>> = flow {
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