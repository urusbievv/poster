package com.example.poster.presentation.viewModel

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poster.domain.model.UserDomain
import com.example.poster.domain.usecase.RegisterUserUseCase

class RegisterViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    private val handler = Handler(Looper.getMainLooper())

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val _registrationLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val registrationLiveData: LiveData<Boolean> = _registrationLiveData

    fun saveUserRegistration(
        name: String, email: String, password: String,
        phone: String
    ) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            handler.post {
                _errorLiveData.value = "Введите все данные для регистрации"
            }
        } else {
            val user = UserDomain(
                name,
                email,
                password,
                phone,
            )
            registerUserUseCase.execute(user) { success ->
                handler.post {
                    _registrationLiveData.value = success
                }
            }
        }
    }
}