package com.shipping.prueba_tecnica_movil.data.network

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:CountryApiClient) {

    suspend fun getCountries(): ApiResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCountries()
            Log.d("response ", "invoke: service " + response )

            response.body() ?: ApiResponse(false , emptyList() )
        }
    }

}