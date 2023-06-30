package ru.veider.bloodpressurediary.domain

data class Measure(
	val date: Long,
	val lowPressure:Int,
	val highPressure: Int,
	val pulse:Int
):Data
