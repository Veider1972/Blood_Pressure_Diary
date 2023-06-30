package ru.veider.bloodpressurediary.ui.main.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import ru.veider.bloodpressurediary.domain.Data

class DataAdapter : ListDelegationAdapter<List<Data>>(titleAdapterDelegate(), measureAdapterDelegate()) {

	init {
		items = emptyList()
	}
}