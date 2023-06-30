package ru.veider.bloodpressurediary.ui.main.ext

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import ru.veider.bloodpressurediary.R
import ru.veider.bloodpressurediary.databinding.ItemMeasureBinding
import kotlin.math.roundToInt

fun ItemMeasureBinding.setBackGroundColor(value: Int) {
	val white = Color.WHITE
	val colorList = intArrayOf(
		root.context.getColor(R.color.pressure_0),
		root.context.getColor(R.color.pressure_10),
		root.context.getColor(R.color.pressure_20),
		root.context.getColor(R.color.pressure_30),
		root.context.getColor(R.color.pressure_40),
		root.context.getColor(R.color.pressure_50),
		root.context.getColor(R.color.pressure_60),
		root.context.getColor(R.color.pressure_70),
		root.context.getColor(R.color.pressure_80),
		root.context.getColor(R.color.pressure_90),
		root.context.getColor(R.color.pressure_100),
	)
	val currentColor = when {
		value <= 0 -> colorList[0]
		value >= 100 -> colorList[colorList.lastIndex]
		else -> {
			val index = (value.toDouble() / 10).roundToInt()
			println(index)
			colorList[index]
		}
	}
	backStart.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(white, currentColor))
	backCenter.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(currentColor, currentColor))
	backEnd.background = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(currentColor, white))
}

