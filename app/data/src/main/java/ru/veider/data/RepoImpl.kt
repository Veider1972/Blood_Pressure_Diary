package ru.veider.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import ru.veider.core.utils.DATE
import ru.veider.core.utils.HIGHPRESSURE
import ru.veider.core.utils.LOWPRESSURE
import ru.veider.core.utils.PULSE
import ru.veider.data.model.ResponseMeasure

class RepoImpl(
	private val db: FirebaseFirestore
) : Repo {
	override fun readData(user: String) = db.collection(user).get()

	override fun addData(user: String, measure: ResponseMeasure): Task<DocumentReference> {
		val outData = hashMapOf(
			DATE to measure.date,
			LOWPRESSURE to measure.lowPressure,
			HIGHPRESSURE to measure.highPressure,
			PULSE to measure.pulse
		)
		return db.collection(user)
			.add(outData)
	}
}