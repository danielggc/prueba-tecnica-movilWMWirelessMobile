package com.shipping.prueba_tecnica_movil.domain

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.data.database.entities.toDatabase
import com.shipping.prueba_tecnica_movil.domain.model.Country
import java.lang.Exception
import javax.inject.Inject

class GetCountryModelUseCase @Inject constructor(private val repository: RemissionRepository) {
    suspend operator fun invoke():List<Country>{

        try {
            val quotes = repository.getAllCountriesFromApi()
            return if(quotes.isNotEmpty()){
                repository.clearAllCountries()
                repository.insertCountry( quotes.map {  it.toDatabase() })
                quotes
            }else{
                repository.getAllCountriesFromDatabase()
            }
        }
        catch ( e : Exception) {
            Log.d("ERROR",  " GetQuotesUseCase invoke: ${e.message}  \n ${e.cause}  \n ${e.localizedMessage}" )

            return repository.getAllCountriesFromDatabase()
        }


    }


}