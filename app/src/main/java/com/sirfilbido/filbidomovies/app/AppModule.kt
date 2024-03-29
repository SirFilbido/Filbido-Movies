package com.sirfilbido.filbidomovies.app

import com.sirfilbido.filbidomovies.data.di.dataModule
import com.sirfilbido.filbidomovies.domain.di.domainModule
import com.sirfilbido.filbidomovies.presentation.di.presentationModule
import org.koin.core.module.Module

fun appModule(): List<Module> = arrayListOf<Module>().apply {
    addAll(dataModule())
    addAll(domainModule())
    addAll(presentationModule())
}