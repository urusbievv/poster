package com.example.poster.domain.detect.usecaseImpl


import com.example.poster.domain.detect.usecase.DetectedObjectDataSource
import com.example.poster.domain.detect.usecase.SaveDetectedObjectUseCase

class SaveDetectedObjectUseCaseImpl(
    private val detectedObjectDataSource: DetectedObjectDataSource
) : SaveDetectedObjectUseCase {

    override suspend fun execute(userId: String, objectName: String) {
        detectedObjectDataSource.saveDetectedObject(userId, objectName)
    }
}
