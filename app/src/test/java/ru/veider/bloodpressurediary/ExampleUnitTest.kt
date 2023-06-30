package ru.veider.bloodpressurediary

import org.junit.Test

import org.junit.Assert.*
import ru.veider.bloodpressurediary.core.utils.getPercentage

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, 2 + 2)
	}

	@Test
	fun getPressure(){
		println("pressure 110 ${getPercentage(110)}")
		println("pressure 115 ${getPercentage(115)}")
		println("pressure 120 ${getPercentage(120)}")
		println("pressure 125 ${getPercentage(125)}")
		println("pressure 130 ${getPercentage(130)}")
		println("pressure 140 ${getPercentage(140)}")
	}
}