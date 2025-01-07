package com.example.custom_date_picker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_date_picker.databinding.ItemListPickerBinding
import com.google.android.material.internal.ViewUtils.dpToPx

class MyAdapter(
    private val items: Array<String>,
    private val context : Context
): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val binding = ItemListPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val layout = holder.root.layoutParams
        layout.height = dpToPx(AppSize.ITEM_LIST_HEIGHT)
        holder.root.layoutParams = layout

        holder.title.text = items[position]
        holder.background.setOnClickListener {
            Log.e("TAG", "onBindViewHolder: ${items[position]}", )
        }
    }

    inner class MyViewHolder(binding: ItemListPickerBinding): RecyclerView.ViewHolder(binding.root){
        val root = binding.root
        val title = binding.titleTxt
        val background = binding.background
    }

    // dp를 px로 변환하는 유틸리티 함수
    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}