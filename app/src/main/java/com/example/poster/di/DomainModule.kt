package com.example.poster.di

import com.example.poster.domain.usecase.EntranceUserUseCase
import com.example.poster.domain.usecase.RegisterUserUseCase

import org.koin.dsl.module


val domainModule = module {
    factory { EntranceUserUseCase(entranceRepository = get()) }
    factory { RegisterUserUseCase(registerRepository = get()) }
}