package ru.veider.bloodpressurediary.presentation.input_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.veider.bloodpressurediary.R
import ru.veider.bloodpressurediary.databinding.DialogFragmentBinding
import ru.veider.bloodpressurediary.presentation.main.ui.MainActivity

class InputDialog : DialogFragment(R.layout.dialog_fragment) {

	private lateinit var binding: DialogFragmentBinding

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		binding = DialogFragmentBinding.inflate(layoutInflater)

		binding.saveButton.setOnClickListener {
			if (checkFieldsReady()){
				(activity as? MainActivity)?.addNewData(
					highPressure = binding.highPressureField.text.toString().toInt(),
					lowPressure = binding.lowPressureField.text.toString().toInt(),
					pulse = binding.pulseField.text.toString().toInt()
				)
				dismiss()
			} else {
				Toast.makeText(requireContext(), getString(R.string.dialog_empty_hint), Toast.LENGTH_SHORT).show()
			}
		}

		binding.cancelButton.setOnClickListener {
			dismiss()
		}


		return AlertDialog.Builder(requireActivity())
			.setView(binding.root)
			.create()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		dialog?.apply {
			window?.setBackgroundDrawableResource(R.drawable.shape_dialog)
			setCanceledOnTouchOutside(false)
		}
	}

	private fun checkFieldsReady() =
		!binding.highPressureField.text.isNullOrEmpty() &&
				!binding.lowPressureField.text.isNullOrEmpty() &&
				!binding.pulseField.text.isNullOrEmpty()

}