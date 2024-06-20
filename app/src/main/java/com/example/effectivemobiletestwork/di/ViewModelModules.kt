package com.example.effectivemobiletestwork.di

import com.example.effectivemobiletestwork.avia.ui.AviaViewModel
import com.example.effectivemobiletestwork.avia.ui.SelectedCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        AviaViewModel(recommendationsInteractor = get(), ticketsInteractor = get())
    }
    viewModel {
        SelectedCountryViewModel(ticketsOffersInteractor = get())
    }
}