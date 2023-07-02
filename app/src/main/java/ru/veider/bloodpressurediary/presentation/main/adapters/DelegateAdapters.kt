package ru.veider.bloodpressurediary.presentation.main.adapters

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.veider.bloodpressurediary.databinding.ItemMeasureBinding
import ru.veider.bloodpressurediary.databinding.ItemTitleBinding
import ru.veider.bloodpressurediary.presentation.main.ext.setBackGroundColor
import ru.veider.core.utils.getPercentage
import ru.veider.domain.Data
import ru.veider.domain.Measure
import ru.veider.domain.Title
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun measureAdapterDelegate() = adapterDelegateViewBinding<Measure, Data, ItemMeasureBinding>(
	viewBinding = {layoutInflater, root -> ItemMeasureBinding.inflate(layoutInflater, root, false) }
){
	bind{
		binding.lowPressure.text = item.lowPressure.toString()
		binding.highPressure.text = item.highPressure.toString()
		binding.pulse.text = item.pulse.toString()
		val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(item.date))
		binding.time.text = time
		binding.setBackGroundColor(getPercentage(item.highPressure))
	}

}

@SuppressLint("SimpleDateFormat")
fun titleAdapterDelegate() = adapterDelegateViewBinding<Title, Data, ItemTitleBinding>(
	viewBinding = {layoutInflater, root -> ItemTitleBinding.inflate(layoutInflater, root, false) }
){
	bind {
		binding.date.text = item.date
	}

}