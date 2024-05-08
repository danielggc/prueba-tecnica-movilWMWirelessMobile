package com.shipping.prueba_tecnica_movil.data.network

import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiClient {
    @GET("/all")
    suspend fun getAllCountries(): Response<ApiResponse>
}