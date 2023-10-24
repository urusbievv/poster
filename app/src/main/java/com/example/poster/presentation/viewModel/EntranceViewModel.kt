package com.example.poster.presentation.viewModel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poster.domain.usecase.EntranceUserUseCase



class EntranceViewModel(private val entranceUserUseCase: EntranceUserUseCase) : ViewModel() {

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private val _successLiveData = MutableLiveData<Boolean>()
    val successLiveData: LiveData<Boolean> get() = _successLiveData

    fun onEntranceClicked(email: String, password: String) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            _errorLiveData.value = "Введите все данные для авторизации"
        } else {
            entranceUserUseCase.execute(
                email,
                password,
                onSuccess = {
                    _successLiveData.value = true
                },
                onFailure = { errorMessage ->

                    _errorLiveData.value = errorMessage
                }
            )
        }
    }

}