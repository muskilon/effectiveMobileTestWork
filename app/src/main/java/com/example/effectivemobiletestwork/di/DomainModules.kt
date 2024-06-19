package com.example.effectivemobiletestwork.di

import com.example.effectivemobiletestwork.avia.data.RecommendationRepositoryImpl
import com.example.effectivemobiletestwork.avia.domain.RecommendationRepository
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractorImpl
import org.koin.dsl.module

val domainModules = module {
    factory<RecommendationsInteractor> { RecommendationsInteractorImpl(repository = get()) }
    single<RecommendationRepository> { RecommendationRepositoryImpl(networkClient = get(), mapper = get()) }
}