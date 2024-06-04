package com.example.androidlesson22.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(): ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun login(email: String, password: String) {
        _loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                Log.e("result", it.isSuccessful.toString())
                _isLogin.value = it.isSuccessful
                _loading.value = false
            }
        }
    }

    fun register(email: String, password: String) {
        _loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _isLogin.value = true
                } else {
                    _error.value = it.exception?.localizedMessage
                }
                _loading.value = false
            }
        }
    }
}