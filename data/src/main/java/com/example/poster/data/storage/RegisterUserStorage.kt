package com.example.poster.data.storage

import com.example.poster.data.model.User

interface RegisterUserStorage {
    fun register(user: User, callback: (Boolean) -> Unit)
}