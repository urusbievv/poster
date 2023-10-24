package com.example.poster.di

import com.example.poster.data.repository.EntranceRepositoryImpl
import com.example.poster.data.repository.RegisterRepositoryImpl
import com.example.poster.data.storage.EntranceUserStorage
import com.example.poster.data.storage.RegisterUserStorage
import com.example.poster.data.storage.repository.EntranceFirebaseStorage
import com.example.poster.data.storage.repository.RegisterFirebaseStorage
import com.example.poster.domain.repository.EntranceRepository
import com.example.poster.domain.repository.RegisterRepository
import org.koin.dsl.module


val dataModule = module {

    single<EntranceUserStorage> { EntranceFirebaseStorage() }
    single<EntranceRepository> { EntranceRepositoryImpl(entranceUserStorage = get()) }
    single<RegisterUserStorage> {RegisterFirebaseStorage()}
    single<RegisterRepository> { RegisterRepositoryImpl(registerUserStorage = get()) }
}