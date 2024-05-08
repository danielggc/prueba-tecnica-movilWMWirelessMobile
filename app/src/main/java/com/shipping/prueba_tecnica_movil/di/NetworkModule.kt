package com.shipping.prueba_tecnica_movil.di

import com.shipping.prueba_tecnica_movil.data.network.CountryApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//https://us-central1-cm-tim-goo-test.cloudfunctions.net/prueba_tecnica/carga_trabajo/equipo/77/terminal/2
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):CountryApiClient{
        return retrofit.create(CountryApiClient::class.java)
    }
}