package ru.veider.bloodpressurediary.presentation.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import org.koin.android.ext.android.inject
import ru.veider.bloodpressurediary.R
import ru.veider.bloodpressurediary.databinding.ActivityMainBinding
import ru.veider.bloodpressurediary.presentation.input_dialog.InputDialog
import ru.veider.bloodpressurediary.presentation.main.adapters.DataAdapter
import ru.veider.bloodpressurediary.presentation.main.vm.MainViewModel
import ru.veider.core.datatype.ScreenState
import ru.veider.domain.Measure
import java.util.Date

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private lateinit var binding: ActivityMainBinding

	private val viewModel : MainViewModel by inject()

	private val adapter by lazy {
		DataAdapter()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		init()
		workToNewMeasure()

	}

	private fun init(){
		binding.dataList.adapter = adapter
		readData()
	}

	private fun workToNewMeasure(){
		binding.fab.setOnClickListener {
			supportFragmentManager
				.beginTransaction()
				.add(InputDialog(), "")
				.commit()
		}
	}

	private fun readData() {
		viewModel.readData().observe(this){
			when (it){
				is ScreenState.Loading -> {
					binding.loading.isVisible = true
					binding.container.isVisible = false
				}
				is ScreenState.Success -> {
					binding.loading.isVisible = false
					binding.container.isVisible = true
					if (it.data.isNotEmpty()){
						binding.noData.isVisible = false
						binding.dataList.isVisible = true
						adapter.items = it.data
						adapter.notifyDataSetChanged()
					} else {
						binding.noData.isVisible = true
						binding.dataList.isVisible = false
					}

				}
				is ScreenState.Error -> {
					binding.loading.isVisible = false
					binding.container.isVisible = false
					Toast.makeText(applicationContext, getString(R.string.measure_get_error), Toast.LENGTH_SHORT).show()
				}
			}
		}
	}

	fun addNewData(lowPressure: Int, highPressure: Int, pulse: Int) {

		val measure = Measure(
			date = Date().time,
			lowPressure = lowPressure,
			highPressure = highPressure,
			pulse = pulse
		)
		viewModel.saveData(measure).observe(this){
			when (it){
				is ScreenState.Loading -> {
					binding.loading.isVisible = true
				}
				is ScreenState.Success -> {
					binding.loading.isVisible = false
					Toast.makeText(applicationContext, getString(R.string.measure_added), Toast.LENGTH_SHORT).show()
					readData()
				}
				is ScreenState.Error -> {
					binding.loading.isVisible = false
					Toast.makeText(applicationContext, getString(R.string.measure_add_error), Toast.LENGTH_SHORT).show()
				}
			}
		}
	}


}