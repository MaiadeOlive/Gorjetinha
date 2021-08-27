package com.example.gorjetinha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gorjetinha.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener { calculateTip() }
    }

    fun calculateTip(){
        val costOfTip = binding.costOfService.text.toString()
        val cost = costOfTip.toDouble()

        val percentOfTip = binding.tipOptions.checkedRadioButtonId
        val percent = when (percentOfTip){
            R.id.tip_choise_1 -> 0.20
            R.id.tip_choise_2 -> 0.18
            R.id.tip_choise_3 -> 0.15
            R.id.tip_choise_4 -> 0.10
            else -> 0.00
        }
        var tip = percent * cost

        val roundUp = binding.switchChoise.isChecked
        if (roundUp) { tip = kotlin.math.ceil(tip)}

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmountFinalCost.text = getString(R.string.tip_amount, formattedTip)
    }
}