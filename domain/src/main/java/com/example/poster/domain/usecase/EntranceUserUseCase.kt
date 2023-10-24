package com.example.poster.domain.usecase

import com.example.poster.domain.repository.EntranceRepository

class EntranceUserUseCase(private val entranceRepository: EntranceRepository) {

    fun execute(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) =
       entranceRepository.entranceUser(email,password, onSuccess, onFailure)
}