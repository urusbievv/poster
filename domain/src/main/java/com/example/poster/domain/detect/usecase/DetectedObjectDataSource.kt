package com.example.poster.domain.detect.usecase

interface DetectedObjectDataSource {
    suspend fun saveDetectedObject(userId: String, objectName: String)
}