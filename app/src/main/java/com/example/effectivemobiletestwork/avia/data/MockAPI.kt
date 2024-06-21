package com.example.effectivemobiletestwork.avia.data

import com.example.effectivemobiletestwork.avia.data.dto.MainRecommendationDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsDTO
import com.example.effectivemobiletestwork.avia.data.dto.TicketsOffersDTO
import retrofit2.Response
import retrofit2.http.GET

interface MockAPI {
    // из задания
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getRecommendations(): Response<MainRecommendationDTO>

    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): Response<TicketsOffersDTO>

    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Response<TicketsDTO>

    // мои
//    @GET("112219af-8eae-4480-b442-707219de1f1b")
//    suspend fun getRecommendations(): Response<MainRecommendationDTO>
//
//    @GET("e82baab1-b1f3-4f70-a05a-ff841b323300")
//    suspend fun getTicketsOffers(): Response<TicketsOffersDTO>
//
//    @GET("c914a35e-8d57-4363-a1cf-1d6127cc5be8")
//    suspend fun getTickets(): Response<TicketsDTO>
}

// 38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a tickets_offers в задании
// e82baab1-b1f3-4f70-a05a-ff841b323300 мои tickets_offers

// ad9a46ba-276c-4a81-88a6-c068e51cce3a рекомендации в задании
// 112219af-8eae-4480-b442-707219de1f1b мои рекомендации

// c0464573-5a13-45c9-89f8-717436748b69 tickets в задании
// c914a35e-8d57-4363-a1cf-1d6127cc5be8 мои tickets