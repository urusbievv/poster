package com.example.poster.domain.repository

interface EntranceRepository {

    fun entranceUser(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

}