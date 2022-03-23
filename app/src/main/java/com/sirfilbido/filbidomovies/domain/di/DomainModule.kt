package com.sirfilbido.filbidomovies.domain.di

import com.sirfilbido.filbidomovies.domain.interactor.GetListNowPlayingUseCase
import org.koin.dsl.module

fun domainModule() = arrayListOf(
    useCaseModule(),
)

private fun useCaseModule() = module {
    factory { GetListNowPlayingUseCase(get()) }
}
