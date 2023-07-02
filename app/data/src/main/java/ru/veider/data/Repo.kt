package ru.veider.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import ru.veider.data.model.ResponseMeasure

interface Repo {
	fun readData(user: String): Task<QuerySnapshot>
	fun addData(user: String, measure: ResponseMeasure): Task<DocumentReference>
}