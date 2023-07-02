package ru.veider.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import ru.veider.domain.Measure

interface UseCases{
	fun readData(user: String): Task<QuerySnapshot>
	fun addData(user: String, measure: Measure): Task<DocumentReference>
}