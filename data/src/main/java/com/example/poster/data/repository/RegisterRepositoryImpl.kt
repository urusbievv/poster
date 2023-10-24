package com.example.poster.data.repository

import com.example.poster.data.model.User
import com.example.poster.data.storage.RegisterUserStorage
import com.example.poster.domain.model.UserDomain
import com.example.poster.domain.repository.RegisterRepository

class RegisterRepositoryImpl(private val registerUserStorage: RegisterUserStorage):
    RegisterRepository {
    override fun registerUser(user: UserDomain, callback: (Boolean) -> Unit)  =
        registerUserStorage.register(mapToStorage(user)) { success ->
            callback(success)
        }

    private fun mapToStorage(userDomain: UserDomain): User =
        User(name = userDomain.name, email = userDomain.email, password = userDomain.password,
            phone = userDomain.phone)
}