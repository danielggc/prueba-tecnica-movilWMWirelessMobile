package com.shipping.prueba_tecnica_movil.data

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.database.dao.CountryDao
import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity
import com.shipping.prueba_tecnica_movil.data.database.entities.toDatabase
import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import com.shipping.prueba_tecnica_movil.data.network.QuoteService
import com.shipping.prueba_tecnica_movil.domain.model.Country
import com.shipping.prueba_tecnica_movil.domain.model.toDomain
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemissionRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: CountryDao
) {

    suspend fun getAllCountriesFromApi(): List<Country> {

        val response: ApiResponse = api.getCountries()
        Log.d("response ", "invoke: service " + response )

        return  response.data.mapIndexed { index, e ->  e.toDomain(index) }
    }

    suspend fun getCountriesByPrefix( prefix: String ):List<Country>{
        val response: List<CountryEntity> = quoteDao.getCountriesByPrefix( prefix )
        return response.map { it.toDomain() }
    }

    suspend fun getAllCountriesFromDatabase():List<Country>{
        val response: List<CountryEntity> = quoteDao.getAllCountries()
        return response.map { it.toDomain() }
    }


    suspend fun updateListCountries(remissionList: List<Country>):Int{
        val updatedRows = withContext(Dispatchers.IO) {
            quoteDao.updateOrder(remissionList.map { it.toDatabase() })
        }
        return updatedRows
    }
    suspend fun  getCountriesInBatchesFromDataBase(pageSize: Int, offset: Int ) : List<Country>{
        val response :List<CountryEntity> = quoteDao.getCountriesInBatches( pageSize ,offset )
        Log.d("DATABASE", "get counties in batches form data base :  " + response)
        return  response.map{it.toDomain()}
    }

    suspend fun insertCountry(remissionModel:List<CountryEntity>){
        Log.d("DATABASE", "insertQuotes:  " + remissionModel )
        quoteDao.insertAll(remissionModel)
    }

    suspend fun clearAllCountries(){
        quoteDao.deleteAllQuotes()
    }
}