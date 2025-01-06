package com.example.custom_date_picker

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.custom_date_picker.databinding.DialogDatePickerBinding

class CustomDialog(
    private val onConfirm:(String) -> Unit
): DialogFragment() {
    private var _binding: DialogDatePickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogDatePickerBinding.inflate(inflater, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatePicker()
    }

    private fun initDatePicker(){
        val currentDate = Calendar.getInstance()

        val currentYear = currentDate.get(Calendar.YEAR)
        val currentMonth = currentDate.get(Calendar.MONTH)+1
        Log.e("TAG", "initDatePicker: $currentYear $currentMonth", )

        with(binding){
            npYear.minValue = currentYear - 1
            npYear.maxValue = currentYear + 1

            npMonth.minValue = 1
            npMonth.maxValue = 12

            npDay.minValue = 1
            npDay.maxValue = getDaysInMonth(currentYear, currentMonth)

            npYear.displayedValues = getDisplayValues(currentYear-1, currentYear+1, "년")
            npMonth.displayedValues = getDisplayValues(1, 12, "월")
            npDay.displayedValues = getDisplayValues(1, 31, "일")

            npYear.value = currentYear
            npMonth.value = currentMonth
            npDay.value = currentDate.get(Calendar.DAY_OF_MONTH)

            // 수정 막기
            npYear.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npMonth.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            npDay.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

            npYear.setOnValueChangedListener { picker, oldVal, newVal ->
                val maxDayValue = getDaysInMonth(newVal, npMonth.value)
                npDay.maxValue = maxDayValue
            }

            npMonth.setOnValueChangedListener { picker, oldVal, newVal ->
                val maxDayValue = getDaysInMonth(npYear.value, newVal)
                npDay.maxValue = maxDayValue
            }

            confirmButton.setOnClickListener {
                val data = "${npYear.value}. ${npMonth.value}. ${npDay.value}"
                Log.e("TAG", "initDatePicker: $data", )
                onConfirm(data)
                dismiss()
            }
        }
    }

    private fun getDaysInMonth(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month-1) // ?
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun getDisplayValues(start: Int, end: Int, suffix: String): Array<String> {
        val displayValues = mutableListOf<String>()
        for(value in start..end){
            displayValues.add("${value}${suffix}")
        }
        return displayValues.toTypedArray()
    }
}