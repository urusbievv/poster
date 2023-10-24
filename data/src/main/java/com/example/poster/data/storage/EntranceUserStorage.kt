package com.example.poster.data.storage

interface EntranceUserStorage {
    fun entrance(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}