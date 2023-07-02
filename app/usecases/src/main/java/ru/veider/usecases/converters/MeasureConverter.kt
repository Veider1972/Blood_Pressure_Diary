package ru.veider.usecases.converters

import ru.veider.data.model.ResponseMeasure
import ru.veider.domain.Measure

fun Measure.toResponseMeasure() =
	ResponseMeasure(
		date = date,
		lowPressure = lowPressure,
		highPressure = highPressure,
		pulse = pulse
	)