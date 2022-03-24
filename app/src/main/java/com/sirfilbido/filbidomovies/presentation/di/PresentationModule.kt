package com.sirfilbido.filbidomovies.presentation.di

import com.sirfilbido.filbidomovies.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presentationModule() = arrayListOf(
    viewModelModule()
)

private fun viewModelModule() = module {
    viewModel { HomeViewModel() }
}