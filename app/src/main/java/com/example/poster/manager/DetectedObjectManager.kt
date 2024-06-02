package com.example.poster.manager

import com.example.poster.domain.detect.usecase.SaveDetectedObjectUseCase

// DetectedObjectManager.kt
class DetectedObjectManager(
    private val saveDetectedObjectUseCase: SaveDetectedObjectUseCase
) {

    suspend fun saveDetectedObject(userId: String, objectName: String) {
        saveDetectedObjectUseCase.execute(userId, objectName)
    }
}
