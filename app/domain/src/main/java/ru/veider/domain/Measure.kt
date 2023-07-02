package ru.veider.domain

data class Measure(
	val date: Long,
	val lowPressure:Int,
	val highPressure: Int,
	val pulse:Int
):Data
