package com.example.effectivemobiletestwork.di

import com.example.effectivemobiletestwork.avia.data.RecommendationRepositoryImpl
import com.example.effectivemobiletestwork.avia.data.TicketsOffersRepositoryImpl
import com.example.effectivemobiletestwork.avia.data.TicketsRepositoryImpl
import com.example.effectivemobiletestwork.avia.domain.RecommendationRepository
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractor
import com.example.effectivemobiletestwork.avia.domain.RecommendationsInteractorImpl
import com.example.effectivemobiletestwork.avia.domain.TicketsInteractor
import com.example.effectivemobiletestwork.avia.domain.TicketsInteractorImpl
import com.example.effectivemobiletestwork.avia.domain.TicketsOffersInteractor
import com.example.effectivemobiletestwork.avia.domain.TicketsOffersInteractorImpl
import com.example.effectivemobiletestwork.avia.domain.TicketsOffersRepository
import com.example.effectivemobiletestwork.avia.domain.TicketsRepository
import org.koin.dsl.module

val domainModules = module {
    factory<RecommendationsInteractor> { RecommendationsInteractorImpl(repository = get()) }
    single<RecommendationRepository> { RecommendationRepositoryImpl(networkClient = get(), mapper = get()) }

    factory<TicketsInteractor> { TicketsInteractorImpl(repository = get()) }
    single<TicketsRepository> { TicketsRepositoryImpl(networkClient = get(), mapper = get()) }

    factory<TicketsOffersInteractor> { TicketsOffersInteractorImpl(repository = get()) }
    single<TicketsOffersRepository> { TicketsOffersRepositoryImpl(networkClient = get(), mapper = get()) }
}