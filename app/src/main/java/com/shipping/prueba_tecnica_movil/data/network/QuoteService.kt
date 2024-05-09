package com.shipping.prueba_tecnica_movil.data.network

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import com.shipping.prueba_tecnica_movil.data.model.CountryDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:CountryApiClient) {

    suspend fun getCountries(): ApiResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCountries()
            Log.d("response ", "invoke: service " + response.body() )
            val r = response.body() ?: emptyList()
            if( response.body().isNullOrEmpty() )   ApiResponse( true , r)
            else ApiResponse(  false , r )
        }
    }

}