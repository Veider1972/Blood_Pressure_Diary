package ru.veider.bloodpressurediary.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ru.veider.bloodpressurediary.R
import ru.veider.bloodpressurediary.databinding.ActivityMainBinding
import ru.veider.bloodpressurediary.domain.Data
import ru.veider.bloodpressurediary.domain.Measure
import ru.veider.bloodpressurediary.domain.Title
import ru.veider.bloodpressurediary.ui.input_dialog.InputDialog
import ru.veider.bloodpressurediary.ui.main.adapters.DataAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private lateinit var binding: ActivityMainBinding

	val db by lazy {
		Firebase.firestore
	}

	private val adapter by lazy {
		DataAdapter()
	}

	private lateinit var user: String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.dataList.adapter = adapter

		user = getUser()

		readData()

		binding.fab.setOnClickListener {
			supportFragmentManager.beginTransaction().add(InputDialog(), "").commit()
		}
	}

	private fun readData() {
		db.collection(user)
			.get()
			.addOnSuccessListener { result ->
				val measureList = mutableListOf<Measure>()
				for (document in result) {
					println(document.data)
					val measure = Measure(
						date = document.data[DATE] as Long,
						lowPressure = (document.data[LOWPRESSURE] as Long).toInt(),
						highPressure = (document.data[HIGHPRESSURE] as Long).toInt(),
						pulse = (document.data[PULSE] as Long).toInt()
					)
					measureList.add(measure)
				}
				if (measureList.isNotEmpty()){
					binding.noData.isVisible = false
					binding.dataList.isVisible = true
					val adapterData = mutableListOf<Data>()
					measureList.sortBy { it.date }
					var currDate = ""
					measureList.forEach { measure ->
						val date = SimpleDateFormat("dd MMMM", Locale.getDefault()).format(Date(measure.date))
						if (currDate != date) {
							adapterData.add(Title(date))
							currDate = date
						}
						adapterData.add(measure)
					}
					adapter.items = adapterData
					adapter.notifyDataSetChanged()
				} else {
					binding.noData.isVisible = true
					binding.dataList.isVisible = false
				}
			}
			.addOnFailureListener {
				Toast.makeText(applicationContext, getString(R.string.measure_get_error), Toast.LENGTH_SHORT).show()
			}
	}

	private fun getUser(): String {
		val pref = getSharedPreferences(PREF, Context.MODE_PRIVATE)
		val value = pref.getString(USER, null)
		value?.run {
			return this
		}
		val newUser = UUID.randomUUID().toString()
		pref.edit()
			.putString(USER, newUser)
			.apply()
		return newUser
	}

	fun addNewData(lowPressure: Int, highPressure: Int, pulse: Int) {
		val measure = hashMapOf(
			DATE to Date().time,
			LOWPRESSURE to lowPressure,
			HIGHPRESSURE to highPressure,
			PULSE to pulse
		)
		db.collection(user)
			.add(measure)
			.addOnSuccessListener {
				Toast.makeText(applicationContext, getString(R.string.measure_added), Toast.LENGTH_SHORT).show()
				readData()
			}
			.addOnFailureListener {
				Toast.makeText(applicationContext, getString(R.string.measure_add_error), Toast.LENGTH_SHORT).show()
			}
	}

	companion object {
		private const val PREF = "pref"
		private const val USER = "user"
		private const val HIGHPRESSURE = "highPressure"
		private const val LOWPRESSURE = "lowPressure"
		private const val PULSE = "pulse"
		private const val DATE = "date"
	}
}