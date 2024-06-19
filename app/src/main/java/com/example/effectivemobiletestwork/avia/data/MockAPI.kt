package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.DTO.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.DTO.TicketsOffersDTO
import com.example.effectivemobiletestwork.domain.Resource
import retrofit2.Response
import retrofit2.http.GET

interface MockAPI {
    @GET("112219af-8eae-4480-b442-707219de1f1b")
    suspend fun getRecommendations(): Response<MainRecommendationDTO>

    @GET("e82baab1-b1f3-4f70-a05a-ff841b323300")
    suspend fun getTicketsOffers(): Resource<TicketsOffersDTO>

    @GET("c914a35e-8d57-4363-a1cf-1d6127cc5be8")
    suspend fun getTickets(): Resource<TicketsDTO>
}