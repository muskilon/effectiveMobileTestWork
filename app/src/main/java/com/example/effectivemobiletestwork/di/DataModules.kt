package com.example.effectivemobiletestwork.di
import android.content.Context
import com.example.effectivemobiletestwork.avia.data.SharedStorage
import com.example.effectivemobiletestwork.avia.data.dto.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.data.network.MockAPI
import com.example.effectivemobiletestwork.avia.data.network.NetworkClient
import com.example.effectivemobiletestwork.avia.data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModules = module {
//    Network
    single<NetworkClient> { RetrofitNetworkClient(androidContext(), mockAPI = get()) }
    single<MockAPI> {
        Retrofit.Builder()
//            .baseUrl("https://idkwid2.free.beeceptor.com/")
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockAPI::class.java)
    }

    single {
        androidContext().getSharedPreferences(
            SharedStorage.DEPARTURE_CITY,
            Context.MODE_PRIVATE
        )
    }

    single { DTOToDataMappers() }
    single { SharedStorage(sharedPreferences = get()) }
}