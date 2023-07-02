package ru.veider.bloodpressurediary.presentation.main.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.veider.core.datatype.ScreenState
import ru.veider.core.utils.DATE
import ru.veider.core.utils.HIGHPRESSURE
import ru.veider.core.utils.LOWPRESSURE
import ru.veider.core.utils.PULSE
import ru.veider.domain.Data
import ru.veider.domain.Measure
import ru.veider.domain.Title
import ru.veider.usecases.UseCases
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MainViewModel(
	private val app: Application,
	private val useCases: UseCases
	) : AndroidViewModel(app) {

	private val readData = MutableLiveData<ScreenState<List<Data>>>()
	private val saveData = MutableLiveData<ScreenState<Boolean>>()

	private lateinit var user: String

	init {
		getUser()
	}

	private fun getUser() {
		val pref = app.getSharedPreferences(PREF, Context.MODE_PRIVATE)
		val value = pref.getString(USER, null)
		value?.run {
			user = this
			return
		}
		val newUser = UUID.randomUUID().toString()
		pref.edit()
			.putString(USER, newUser)
			.apply()
		user = newUser
	}

	fun readData(): LiveData<ScreenState<List<Data>>> {
		readData.postValue(ScreenState.Loading())
		useCases.readData(user)
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
					readData.postValue(ScreenState.Success(adapterData))
				}
			}
			.addOnFailureListener {
				readData.postValue(ScreenState.Error(it))
			}
		return readData
	}

	fun saveData(measure: Measure):LiveData<ScreenState<Boolean>> {
		saveData.postValue(ScreenState.Loading())
		useCases.addData(user, measure)
			.addOnSuccessListener {
				saveData.postValue(ScreenState.Success(true))
			}
			.addOnFailureListener {
				saveData.postValue(ScreenState.Error(it))
			}
		return saveData
	}

	companion object{
		private const val PREF = "pref"
		private const val USER = "user"
	}
}