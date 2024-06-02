package com.example.poster.data.detect.dataSource

import com.example.poster.data.detect.repository.DetectedObjectRepository
import com.example.poster.domain.detect.usecase.DetectedObjectDataSource as DomainDetectedObjectDataSource

class DetectedObjectDataSource(
    private val detectedObjectRepository: DetectedObjectRepository
) : DomainDetectedObjectDataSource {

    override suspend fun saveDetectedObject(userId: String, objectName: String) {
        detectedObjectRepository.saveDetectedObject(userId, objectName)
    }
}