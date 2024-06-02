package com.example.poster.data.detect.repository

interface DetectedObjectRepository {
    suspend fun saveDetectedObject(userId: String, objectName: String)
}