package com.shipping.test_cordinadora.ui.viewmodel

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shipping.test_cordinadora.data.model.User
import com.example.loginpage.util.LoginResult
import com.example.loginpage.util.PREF_USERNAME
import com.example.loginpage.util.PREF_USER_PASSWORD
import com.google.firebase.auth.FirebaseAuth

class UserViewModel(application: Application) : AndroidViewModel(application) {


    var user: User = User()
    val preferences = PreferenceManager.getDefaultSharedPreferences(application)
    var loginResult = MutableLiveData<Int>()
    var passwordValidation = MutableLiveData<Int>()
    var usernameValidation = MutableLiveData<Int>()
    var passwordConfirmation = ObservableField<String>()
    private lateinit var  auth: FirebaseAuth

    fun login() {
        validateUsername()
        validatePassword()

    }



    private fun writeUserToSharedPref() {
        val editor = preferences.edit()
        editor.putString(PREF_USERNAME, user.username)
        editor.putString(PREF_USER_PASSWORD, user.password)
        editor.apply()
    }



    private fun validatePassword() {
        when {
            user.password.isEmpty() -> passwordValidation.value = LoginResult.EMPTY_PASSWORD.value
            user.password.length > 4  -> passwordValidation.value = LoginResult.LONG_PASSWORD.value
            else -> passwordValidation.value = LoginResult.OK.value
        }
    }
    private fun validateEmail() {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+"
        val emailMatcher = Regex(emailPattern)
        when {
            !emailMatcher.matches(user.username) -> usernameValidation.value = LoginResult.INVALID_EMAIL.value
            else -> usernameValidation.value = LoginResult.OK.value
        }
    }

    private fun validateUsername() {

        when {
            user.username.isEmpty() -> {
                usernameValidation.value = LoginResult.EMPTY_USERNAME.value
            }
            user.username.length < 5 -> usernameValidation.value = LoginResult.SHORT_USERNAME.value

            else -> usernameValidation.value = LoginResult.OK.value
        }
    }

    private fun isInputValid() =
        passwordValidation.value == LoginResult.OK.value && usernameValidation.value == LoginResult.OK.value


}