package com.shipping.prueba_tecnica_movil.data

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.database.dao.RemissionDao
import com.shipping.prueba_tecnica_movil.data.database.entities.RemissionEntity
import com.shipping.prueba_tecnica_movil.data.database.entities.toDatabase
import com.shipping.prueba_tecnica_movil.data.model.ApiResponse
import com.shipping.prueba_tecnica_movil.data.network.QuoteService
import com.shipping.prueba_tecnica_movil.domain.model.Country
import com.shipping.prueba_tecnica_movil.domain.model.Remission
import com.shipping.prueba_tecnica_movil.domain.model.toDomain
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemissionRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: RemissionDao
) {

    suspend fun getAllQuotesFromApi(): List<Country> {
        val response: ApiResponse = api.getCountries()

        return  response.data.mapIndexed { index, e -> e.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Remission>{
        val response: List<RemissionEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }


    suspend fun actualizarRemisiones(remissionList: List<Remission>):Int{
        val updatedRows = withContext(Dispatchers.IO) {
            quoteDao.updateOrder(remissionList.map { it.toDatabase() })
        }
        return updatedRows
    }
    suspend fun  getRemissionsInBatchesFromDataBAse( pageSize: Int, offset: Int ) : List<Remission>{
        val response :List<RemissionEntity> = quoteDao.getRemissionsInBatches( pageSize ,offset )
        Log.d("DATABASE", "getRemissionsInBatchesFromDataBAse:  " + response)
        return  response.map{it.toDomain()}
    }

    suspend fun insertQuotes(remissionModel:List<RemissionEntity>){
        Log.d("DATABASE", "insertQuotes:  " + remissionModel )

        quoteDao.insertAll(remissionModel)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}