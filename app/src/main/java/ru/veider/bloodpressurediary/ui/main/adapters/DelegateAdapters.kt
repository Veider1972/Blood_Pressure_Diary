package ru.veider.bloodpressurediary.ui.main.adapters

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.veider.bloodpressurediary.core.utils.getPercentage
import ru.veider.bloodpressurediary.databinding.ItemMeasureBinding
import ru.veider.bloodpressurediary.databinding.ItemTitleBinding
import ru.veider.bloodpressurediary.domain.Data
import ru.veider.bloodpressurediary.domain.Measure
import ru.veider.bloodpressurediary.domain.Title
import ru.veider.bloodpressurediary.ui.main.ext.setBackGroundColor
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