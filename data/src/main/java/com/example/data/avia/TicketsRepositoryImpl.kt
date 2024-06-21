package com.example.data.avia

import com.example.data.avia.dto.DTOToDataMappers
import com.example.data.avia.network.NetworkClient
import com.example.domain.avia.repository.TicketsRepository
import com.example.domain.avia.model.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TicketsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DTOToDataMappers
) : TicketsRepository {
    override suspend fun getTickets(): Flow<com.example.domain.Resource<List<Ticket>>> = flow {
        when (val response = networkClient.getTickets()) {
            is com.example.domain.Resource.Data -> {
                with(response) {
                    val data = mapper.ticketsDTOToTickets(this.value.tickets)
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