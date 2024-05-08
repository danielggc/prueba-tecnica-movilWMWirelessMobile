package com.shipping.prueba_tecnica_movil.domain

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.data.database.entities.toDatabase
import com.shipping.prueba_tecnica_movil.domain.model.Remission
import java.lang.Exception
import javax.inject.Inject

class GetRemissionModelUseCase @Inject constructor(private val repository: RemissionRepository) {
    suspend operator fun invoke():List<Remission>{

        try {
            val quotes = repository.getAllQuotesFromApi()
            return if(quotes.isNotEmpty()){
                repository.clearQuotes()
                repository.insertQuotes( quotes.map {  it.toDatabase() })
                quotes
            }else{
                repository.getAllQuotesFromDatabase()
            }
        }
        catch ( e : Exception) {
            Log.d("ERROR",  " GetQuotesUseCase invoke: ${e.message}" )
            return repository.getAllQuotesFromDatabase()
        }


    }


}