package ru.veider.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.data.Repo
import ru.veider.data.RepoImpl


val dataModule = module {


	fun provideFirestore(): FirebaseFirestore = Firebase.firestore


	single { provideFirestore() }
	singleOf(::RepoImpl) { bind<Repo>() }
}