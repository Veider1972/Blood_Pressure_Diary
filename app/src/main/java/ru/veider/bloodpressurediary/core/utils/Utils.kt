package ru.veider.bloodpressurediary.core.utils

fun getPercentage(measure: Int): Int {
	val maxPressure = 140
	val minPressure = 110
	return when {
		measure >= maxPressure -> 100
		measure <= minPressure -> 0
		else -> (measure-minPressure)*100/(maxPressure-minPressure)
	}
}