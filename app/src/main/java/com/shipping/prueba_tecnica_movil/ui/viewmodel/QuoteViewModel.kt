package com.shipping.prueba_tecnica_movil.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.shipping.prueba_tecnica_movil.domain.GetCountryModelUseCase
import com.shipping.prueba_tecnica_movil.domain.GetRandomCountryUseCase
import com.shipping.prueba_tecnica_movil.domain.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetCountryModelUseCase,
    private val getRandomRemissionUseCase: GetRandomCountryUseCase,

    ) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }



    fun updateRemissionList(remissionList: List<Country>) {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val list: List<Country> = remissionList.mapIndexedNotNull { index, remission ->
                    if (remission.positionCounter != index) {
                        remission.copy(positionCounter = index)
                    } else {
                        null
                    }
                }

                getRandomRemissionUseCase.updateRemissionList(list)
                Log.d("SAVEDATA" , "here my data ${remissionList}")
                isLoading.postValue(false)

            } catch (e: Exception) {
                Log.e("Error", "Error al actualizar la lista de remisiones: ${e.message}")
                isLoading.postValue(false)

            }
        }
    }

    fun getCountryByParts(pageSize: Int, offset: Int): LiveData<List<Country>> {
        Log.d("TAG", "get countries by parts: $offset")
        return liveData{
            val response = getRandomRemissionUseCase.getCountriesInBatches(pageSize, offset)
            emit(response)
        }
    }


    fun getCountryByPrefix(prefix:String ): LiveData<List<Country>> {
        Log.d("TAG", "get countries for prefix : $prefix")
        return liveData{
            val response = getRandomRemissionUseCase.getCountriesByPrefix( prefix )
            emit(response)
        }
    }




    fun onCreate() {
        Log.d("init ", "init get data ")

        isLoading.postValue(true)
        viewModelScope.launch {
            val result = getQuotesUseCase()
            Log.d("sabeDataBAse ", "invoke: " + result)
            isLoading.postValue(false)

        }
    }




}