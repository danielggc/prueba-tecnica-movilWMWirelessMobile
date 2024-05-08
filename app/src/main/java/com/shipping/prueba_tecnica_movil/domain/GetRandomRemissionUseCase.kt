package com.shipping.prueba_tecnica_movil.domain

import android.util.Log
import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.domain.model.Remission
import javax.inject.Inject

class GetRandomRemissionUseCase @Inject constructor(private val repository: RemissionRepository) {


    suspend fun getRemissionsInBatches( pageSize: Int, offset: Int ) :List<Remission>{
        Log.d("TAG", "getRemissionsInBatches: "+ pageSize )
        val list = repository.getRemissionsInBatchesFromDataBAse( pageSize, offset )
        Log.d("TAG", "getRemissionsInBatches: "+ list )

        if( !list.isNullOrEmpty() ){
            return list
        }
        //TODO aca deben de ir casos de errores
        return emptyList()
    }


    suspend fun updateRemissionList( remissionList: List<Remission> ){
        //TODO hacer validaciones aca
        val response = repository.actualizarRemisiones(remissionList)
        Log.d("TAG", "updateRemissionList: $response ")

    }


}