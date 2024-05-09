package com.shipping.prueba_tecnica_movil.ui.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shipping.prueba_tecnica_movil.domain.model.Country

class RemisionViewModel : ViewModel() {
    private val _remisionLiveData = MutableLiveData<Country>()
    val remisionLiveData: LiveData<Country> = _remisionLiveData

    fun enviarRemision(remision: Country) {
        Log.d("Correct", "enviarRemision: ")
        _remisionLiveData.value = remision
    }
}