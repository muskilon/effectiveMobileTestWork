package com.example.effectivemobiletestwork.di
import com.example.effectivemobiletestwork.avia.data.dto.DTOToDataMappers
import com.example.effectivemobiletestwork.avia.data.MockAPI
import com.example.effectivemobiletestwork.avia.data.NetworkClient
import com.example.effectivemobiletestwork.avia.data.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModules = module {
//    Network
    single<NetworkClient> { RetrofitNetworkClient(androidContext(), mockAPI = get()) }
    single<MockAPI> {
        Retrofit.Builder()
            .baseUrl("https://idkwid2.free.beeceptor.com/")
//            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockAPI::class.java)
    }

    single { DTOToDataMappers() }
}