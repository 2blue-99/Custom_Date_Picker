package com.example.custom_date_picker

import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_date_picker.databinding.DialogListPickerBinding

class CustomListPickerDialog: DialogFragment() {
    private var _binding: DialogListPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(requireContext())
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogListPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        with(binding.titleList){
            val items = PriceType.toList().toTypedArray()
            adapter = MyAdapter(items, requireContext())
            layoutManager = LinearLayoutManager(requireContext())
            val recyclerViewHeightInPx = dpToPx(40 * 5 + 20)
            val layoutParams = layoutParams
            layoutParams.height = recyclerViewHeightInPx
            this.layoutParams = layoutParams

            // 커스텀 구분선 적용
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            val dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            dividerItemDecoration.setDrawable(dividerDrawable!!)

            addItemDecoration(CustomDividerItemDecoration(requireContext()))
        }
    }

    // dp를 px로 변환하는 유틸리티 함수
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }
}

class CustomDividerItemDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable?

    init {
        // 커스텀 구분선 스타일 (예: 1dp 두께의 회색 선)
        divider = ContextCompat.getDrawable(context, R.drawable.divider)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val layoutManager = parent.layoutManager as LinearLayoutManager

        // 수직 구분선의 경우
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
            for (i in 0 until childCount - 1) {  // 마지막 아이템은 제외하고 그립니다
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams

                val left = child.left - params.leftMargin
                val right = child.right + params.rightMargin
                val top = child.bottom + params.bottomMargin
                val bottom = top + divider!!.intrinsicHeight

                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }

}