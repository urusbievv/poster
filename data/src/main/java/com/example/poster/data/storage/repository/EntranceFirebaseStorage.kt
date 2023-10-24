package com.example.poster.data.storage.repository
import android.app.Application
import android.content.Context
import android.util.Log
import com.example.poster.data.storage.EntranceUserStorage
import com.example.poster.data.utils.ConstantStorage.ENTRANCE_ERROR_MESSAGE
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class EntranceFirebaseStorage() : EntranceUserStorage {

    private val authStorage = FirebaseAuth.getInstance()

    override fun entrance(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        authStorage
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                val errorMessage = e.message ?: ENTRANCE_ERROR_MESSAGE
                onFailure(errorMessage)
            }
    }
}