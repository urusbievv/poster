package com.example.poster.domain.repository

import com.example.poster.domain.model.UserDomain

interface RegisterRepository {
    fun registerUser(user: UserDomain, callback: (Boolean) -> Unit)
}