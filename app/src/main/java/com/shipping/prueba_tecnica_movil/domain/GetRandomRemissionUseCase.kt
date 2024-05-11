package com.shipping.prueba_tecnica_movil.domain

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.domain.model.Country
import javax.inject.Inject

class GetRandomRemissionUseCase @Inject constructor(private val repository: RemissionRepository) {


    suspend fun getCountriesInBatches(pageSize: Int, offset: Int ) :List<Country>{
        Log.d("GET", "getCountriesInBatches: $pageSize")
        val list = repository.getCountriesInBatchesFromDataBase( pageSize, offset )
        Log.d("GET", "getCountriesInBatches: $list")

        if( !list.isNullOrEmpty() ){
            return list
        }
        //TODO aca deben de ir casos de errores
        return emptyList()
    }
    suspend fun getCountriesByPrefix( prefix:String  ) :List<Country>{
        val list = repository.getCountriesByPrefix( prefix  )
        if( !list.isNullOrEmpty() ){
            return list
        }
        //TODO aca deben de ir casos de errores
        return emptyList()
    }



    suspend fun updateRemissionList( remissionList: List<Country> ){
        //TODO hacer validaciones aca
        val response = repository.updateListCountries(remissionList)
        Log.d("TAG", "updateRemissionList: $response ")

    }


}