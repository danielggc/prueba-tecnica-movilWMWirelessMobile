package com.shipping.prueba_tecnica_movil.data.network

import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import com.shipping.prueba_tecnica_movil.data.model.CountryDto
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiClient {
    @GET("all")
    suspend fun getAllCountries(): Response<List<CountryDto>>
}