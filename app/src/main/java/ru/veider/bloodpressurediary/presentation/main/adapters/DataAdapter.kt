package ru.veider.bloodpressurediary.presentation.main.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.veider.domain.Data

class DataAdapter : ListDelegationAdapter<List<Data>>(titleAdapterDelegate(), measureAdapterDelegate()) {

	init {
		items = emptyList()
	}
}