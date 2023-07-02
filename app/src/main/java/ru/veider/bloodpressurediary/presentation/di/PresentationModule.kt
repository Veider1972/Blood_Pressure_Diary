package ru.veider.bloodpressurediary.presentation.di

import ru.veider.bloodpressurediary.presentation.main.vm.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {

	factoryOf(::MainViewModel)

}