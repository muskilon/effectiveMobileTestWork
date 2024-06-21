package com.example.effectivemobiletestwork.di

import com.example.data.avia.RecommendationRepositoryImpl
import com.example.data.avia.SharedRepositoryImpl
import com.example.data.avia.TicketsOffersRepositoryImpl
import com.example.data.avia.TicketsRepositoryImpl
import com.example.domain.avia.DepartureCityInteractor
import com.example.domain.avia.repository.RecommendationRepository
import com.example.domain.avia.RecommendationsInteractor
import com.example.domain.avia.impl.RecommendationsInteractorImpl
import com.example.domain.avia.TicketsInteractor
import com.example.domain.avia.impl.TicketsInteractorImpl
import com.example.domain.avia.TicketsOffersInteractor
import com.example.domain.avia.impl.DepartureCityInteractorImpl
import com.example.domain.avia.impl.TicketsOffersInteractorImpl
import com.example.domain.avia.repository.SharedRepository
import com.example.domain.avia.repository.TicketsOffersRepository
import com.example.domain.avia.repository.TicketsRepository
import org.koin.dsl.module

val domainModules = module {
    factory<RecommendationsInteractor> { RecommendationsInteractorImpl(repository = get()) }
    single<RecommendationRepository> { RecommendationRepositoryImpl(networkClient = get(), mapper = get()) }

    factory<TicketsInteractor> { TicketsInteractorImpl(repository = get()) }
    single<TicketsRepository> { TicketsRepositoryImpl(networkClient = get(), mapper = get()) }

    factory<TicketsOffersInteractor> { TicketsOffersInteractorImpl(repository = get()) }
    single<TicketsOffersRepository> { TicketsOffersRepositoryImpl(networkClient = get(), mapper = get()) }

    factory<DepartureCityInteractor> { DepartureCityInteractorImpl(repository = get()) }
    single<SharedRepository> { SharedRepositoryImpl(sharedStorage = get()) }
}