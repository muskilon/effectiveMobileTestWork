package com.example.effectivemobiletestwork.avia.data.DTO

import com.example.effectivemobiletestwork.avia.domain.MainRecommendation

class DTOToDataMappers {
    fun recommendationsDTOToMainRecommendations(): List<MainRecommendation>{
        return listOf(MainRecommendation(
            id = "mock",
            title = "mock",
            town = "mock",
            price = "mock"
        ))
    }
}