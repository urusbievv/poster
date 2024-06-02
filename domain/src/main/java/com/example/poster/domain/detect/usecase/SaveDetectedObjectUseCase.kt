package com.example.poster.domain.detect.usecase

interface SaveDetectedObjectUseCase {
    suspend fun execute(userId: String, objectName: String)
}