package ru.veider.data.model

data class ResponseMeasure(
	val date: Long,
	val lowPressure:Int,
	val highPressure: Int,
	val pulse:Int
)
