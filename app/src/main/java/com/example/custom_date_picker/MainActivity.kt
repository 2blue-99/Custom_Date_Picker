package com.example.custom_date_picker

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.custom_date_picker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val displayMetrics = resources.displayMetrics
        AppSize.initialize(displayMetrics.widthPixels, displayMetrics.heightPixels)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.testBtn.setOnClickListener {
            showListDialog()
        }
    }

    private fun showDialog(){
        val dialog = CustomDialog(
            onConfirm = {onConfirm(it)}
        )
        dialog.show(this.supportFragmentManager, "custom")
    }

    private fun showListDialog(){
        val dialog = CustomListPickerDialog()
        dialog.show(this.supportFragmentManager, "custom")
    }

    private fun onConfirm(date: String){
        binding.testBtn.text = date
    }
}