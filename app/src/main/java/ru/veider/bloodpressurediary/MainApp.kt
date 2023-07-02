package ru.veider.bloodpressurediary

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.veider.bloodpressurediary.presentation.di.presentationModule
import ru.veider.data.di.dataModule
import ru.veider.usecases.di.useCasesModule

class MainApp:Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@MainApp)
			modules(useCasesModule, dataModule, presentationModule)
		}
	}
}