package com.example.poster.data.detect.repositoryImpl

import com.example.poster.data.detect.repository.DetectedObjectRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class FirebaseDetectedObjectRepository(private val firebaseFirestore: FirebaseFirestore) :
    DetectedObjectRepository {

    companion object {
        const val COLLECTION_NAME = "detected_objects"
    }

    override suspend fun saveDetectedObject(userId: String, objectName: String) {
        val detectedObject = hashMapOf(
            "user_id" to userId,
            "object_name" to objectName,
            "timestamp" to FieldValue.serverTimestamp()
        )

        firebaseFirestore.collection(COLLECTION_NAME)
            .add(detectedObject)
            .await()
    }
}