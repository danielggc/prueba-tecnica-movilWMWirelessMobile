package com.shipping.prueba_tecnica_movil.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.shipping.prueba_tecnica_movil.BR

class User : BaseObservable() {
    @get:Bindable
    var username: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}