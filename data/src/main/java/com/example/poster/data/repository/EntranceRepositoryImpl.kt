package com.example.poster.data.repository

import com.example.poster.data.storage.EntranceUserStorage
import com.example.poster.domain.repository.EntranceRepository

class EntranceRepositoryImpl(private val entranceUserStorage: EntranceUserStorage):
    EntranceRepository {
    override fun entranceUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) = entranceUserStorage.entrance(email,password,onSuccess,onFailure)
}