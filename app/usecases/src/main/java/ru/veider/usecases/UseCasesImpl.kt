package ru.veider.usecases

import ru.veider.data.Repo
import ru.veider.domain.Measure
import ru.veider.usecases.converters.toResponseMeasure

class UseCasesImpl(
	private val repo: Repo
) : UseCases {

	override fun readData(user: String) =
		repo.readData(user)

	override fun addData(user: String, measure: Measure) =
		repo.addData(user, measure.toResponseMeasure())
}