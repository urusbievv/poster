package com.example.poster.di

import com.example.poster.presentation.viewModel.EntranceViewModel
import com.example.poster.presentation.viewModel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel { EntranceViewModel(entranceUserUseCase = get()) }
    viewModel { RegisterViewModel(registerUserUseCase = get()) }
}