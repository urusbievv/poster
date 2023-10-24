package com.example.poster.domain.usecase

import com.example.poster.domain.model.UserDomain
import com.example.poster.domain.repository.RegisterRepository

class RegisterUserUseCase(private val registerRepository: RegisterRepository) {

    fun execute(user: UserDomain, callback: (Boolean) -> Unit) =
        registerRepository.registerUser(user) { success ->
            callback(success)
        }
}