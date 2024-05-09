package com.shipping.prueba_tecnica_movil.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.shipping.prueba_tecnica_movil.domain.GetRemissionModelUseCase
import com.shipping.prueba_tecnica_movil.domain.GetRandomRemissionUseCase
import com.shipping.prueba_tecnica_movil.domain.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetRemissionModelUseCase,
    private val getRandomRemissionUseCase: GetRandomRemissionUseCase,

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
    fun getRemissionsInBatches(pageSize: Int, offset: Int): LiveData<List<Country>> {
        Log.d("TAG", "getRemissionsInBatches: "+  offset)
        return liveData{
            val response = getRandomRemissionUseCase.getRemissionsInBatches(pageSize, offset)
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